package com.example.newspeed.controller;

import com.example.newspeed.common.Const;
import com.example.newspeed.dto.SignUpRequestDto;
import com.example.newspeed.dto.SignUpResponseDto;
import com.example.newspeed.dto.UserResponseDto;
import com.example.newspeed.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<SignUpResponseDto> login(
            @RequestBody SignUpRequestDto requestDto,
            HttpServletRequest servletRequest ){
        SignUpResponseDto login = userService.login(requestDto.getPassword(), requestDto.getUserEmail());
        String userId = login.getUserEmail();
        if (userId == null) {
            return null;
        }

        HttpSession session = servletRequest.getSession();

        UserResponseDto loginUser = userService.findByEmail(userId);
        session.setAttribute(Const.LOGIN_USER, loginUser);

        // 로그인 성공시 리다이렉트
        return new ResponseEntity<>(login, HttpStatus.CREATED);
    }

}
