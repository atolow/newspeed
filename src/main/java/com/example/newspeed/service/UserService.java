package com.example.newspeed.service;

import com.example.newspeed.dto.UserResponseDto;
import com.example.newspeed.entity.User;
import com.example.newspeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto findById(long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다.");
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    public void updatePassword(long id, String oldPassword ,String newPassword) {

        User user = userRepository.findByIdOrElseThrow(id);

        if (!user.getPassword().equals(oldPassword)) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        if (user.getPassword().equals(newPassword)) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "현재 비밀번호와 다른 비밀번호를 입력해주세요.");
        }

        user.updatePassword(newPassword);
    }
}
