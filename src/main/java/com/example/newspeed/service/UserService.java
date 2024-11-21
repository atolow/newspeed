package com.example.newspeed.service;

import com.example.newspeed.dto.SignUpRequestDto;
import com.example.newspeed.dto.SignUpResponseDto;
import com.example.newspeed.entity.User;
import com.example.newspeed.repository.UserRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
@Service
public class UserService {

    private final UserRepository userRepository;
    public final PasswordEncoding passwordEncoding;

    public UserService(UserRepository userRepository, PasswordEncoding passwordEncording) {
        this.userRepository = userRepository;
        this.passwordEncoding = passwordEncording;
    }

    public SignUpResponseDto createUser(SignUpRequestDto requestDto) {
        //이메일이 빈 값 혹은 형식에 맞지 않을 때
        if(!isValidEmail(requestDto.getUserEmail()) || requestDto.getUserEmail().isBlank() ) {
            log.info("!! 이메일형식오류 또는 이메일값.isBlank : {}",requestDto.getUserEmail().isBlank());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email 형식에 맞춰 작성해주세요.");
        }

        // 패스워드가 형식에 맞지 않을 때
        if(!isValidPassword(requestDto.getPassword())){
            log.info("!! 비밀번호 형식에 안맞음 ");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "password 형식에 맞춰 작성해주세요");
        }
        //패드워드에 Bcrypt 인코딩
        String encodedPw = passwordEncoding.encode(requestDto.getPassword());
        log.info("--- encodedPw : {}",encodedPw);

        //builder 형식으로 사용하게 되면 리소스는 많이 들지만 필요한 값은 @Nonnull로만 잡아주고
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
        userRepository.findAll().forEach((item)->{
            SignUpResponseDto oneUser = new SignUpResponseDto(item);
            allUser.add(oneUser);
        });
        return allUser;
    }

    // email로 User조회

    public boolean isValidEmail(String email){
        String emailRegex =
                "^[a-zA-Z0-9_+&*-]+(?:\\." +
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
        return  email.matches(emailRegex);
    }

    //대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 , 8글자 이상
    public boolean isValidPassword(String pw){
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9ㄱ-힣]).{8,20}$";
        Matcher matchPw;
        matchPw = Pattern.compile(passwordRegex).matcher(pw);
        return matchPw.find();
    }

    // 비밀번호 변경하실 때 아래와 같이 .matches 만 호출 하시면 됩니다.
    public void isMatchEncodedPassword(String oldPassword, String newPassword){
        passwordEncoding.matches(oldPassword,newPassword);
    }
}