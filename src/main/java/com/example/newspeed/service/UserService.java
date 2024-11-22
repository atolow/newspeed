package com.example.newspeed.service;

import com.example.newspeed.dto.*;
import com.example.newspeed.entity.User;
import com.example.newspeed.exception.PasswordEncoding;
import com.example.newspeed.repository.UserRepository;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Optional;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public final PasswordEncoding passwordEncoding;
    private final HttpSession httpSession;
    private final ServletRequest httpServletRequest;

    public UserResponseDto findByEmail(String id) {

        Optional<User> optionalUser = userRepository.findByUserEmail(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다.");
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUserName(), findUser.getUserEmail(), findUser.getCreatedAt(), findUser.getModifiedAt(), findUser.getAge(), findUser.getInterests());
    }

    @Transactional
    public void updatePassword(String id, String oldPassword, String newPassword) {

        User user = userRepository.findByUserEmailOrElseThrow(id);

        if (!passwordEncoding.matches(oldPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        if (oldPassword.equals(newPassword)) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "현재 비밀번호와 다른 비밀번호를 입력해주세요.");
        }

        user.updatePassword(passwordEncoding.encode(newPassword));
        userRepository.save(user);
    }

    @Transactional
    public void updateProfile(String id, UpdateProfileRequestDto dto) {

        User user = userRepository.findByUserEmailOrElseThrow(id);

        user.updateProfile(dto.getUserName(), dto.getAge(), dto.getInterests());
        userRepository.save(user);
    }

    public SignUpResponseDto createUser(SignUpRequestDto requestDto) {
        //이메일이 빈 값 혹은 형식에 맞지 않을 때
        if (!isValidEmail(requestDto.getUserEmail())) {
            log.info("!! 이메일형식오류 또는 이메일값.isBlank : {}");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email 형식에 맞춰 작성해주세요.");
        }
        // 패스워드가 형식에 맞지 않을 때
        if (!isValidPassword(requestDto.getPassword())) {
            log.info("!! 비밀번호 형식에 안맞음 ");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "password 형식에 맞춰 작성해주세요");
        }
        //패스워드에 Bcrypt 인코딩
        String encodedPw = passwordEncoding.encode(requestDto.getPassword());
        log.info("--- encodedPw : {}", encodedPw);
        // builder 형식으로 사용하게 되면 리소스는 많이 들지만 필요한 값은 @Nonnull로만 잡아주고
        // 해당 값이 있으면 추가, 없으면 자동으로 null 값을 넣어주는 생성자 방식입니다.
        User user = User.builder()
                .userEmail(requestDto.getUserEmail())
                .password(encodedPw)
                .userName(requestDto.getUserName())
                .age(requestDto.getAge())
                .interests(requestDto.getInterests())
                .build();
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(savedUser);
    }

    // 전체 User 조회
    public List<SignUpResponseDto> searchAllUser() {
        List<SignUpResponseDto> allUser = new ArrayList<>();
        userRepository.findAll().forEach((item) -> {
            SignUpResponseDto oneUser = new SignUpResponseDto(item);
            allUser.add(oneUser);
        });
        return allUser;
    }

    // login
    public SignUpResponseDto login(SignUpRequestDto requestDto, HttpServletRequest servletRequest) {
        Optional<User> byUserEmail = userRepository.findByUserEmail(requestDto.getUserEmail());
        HttpSession session = servletRequest.getSession();
        session.setAttribute("loginUser", byUserEmail.get());
        return new SignUpResponseDto(byUserEmail.get());
    }

    public boolean isValidEmail(String userEmail) {
        String emailRegex =
                "^[a-zA-Z0-9_+&*-]+(?:\\." +
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
        boolean matchedEmail = userEmail.matches(emailRegex);
        boolean alreadyIsEmail = userRepository.findByUserEmail(userEmail).isPresent();
        return matchedEmail && !alreadyIsEmail;
    }

    //대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 , 8글자 이상
    public boolean isValidPassword(String pw) {
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9ㄱ-힣]).{8,20}$";
        Matcher matchPw;
        matchPw = Pattern.compile(passwordRegex).matcher(pw);
        return matchPw.find();
    }
}
