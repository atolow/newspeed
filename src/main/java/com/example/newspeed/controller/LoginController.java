package com.example.newspeed.controller;

import com.example.newspeed.dto.SignUpRequestDto;
import com.example.newspeed.dto.SignUpResponseDto;
import com.example.newspeed.entity.User;
import com.example.newspeed.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
        SignUpResponseDto login = userService.login(requestDto, servletRequest);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

}
