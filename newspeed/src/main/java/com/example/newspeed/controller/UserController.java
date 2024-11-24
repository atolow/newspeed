package com.example.newspeed.controller;

import com.example.newspeed.common.Const;
import com.example.newspeed.dto.UpdatePasswordRequestDto;
import com.example.newspeed.dto.UpdateProfileRequestDto;
import com.example.newspeed.dto.UpdateWithdrawRequestDto;
import com.example.newspeed.dto.UserResponseDto;
import com.example.newspeed.entity.User;
import com.example.newspeed.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/myprofile")
    public ResponseEntity<UserResponseDto> findByEmail(HttpSession httpSession) {

        UserResponseDto userId = (UserResponseDto) httpSession.getAttribute(Const.LOGIN_USER);

        UserResponseDto dto = userService.findByEmail(userId.getEmail());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/updatepassword")
    public ResponseEntity<Void> updatePassword(
            HttpSession httpSession,
            @RequestBody UpdatePasswordRequestDto dto
            ) {

        UserResponseDto userId = (UserResponseDto) httpSession.getAttribute(Const.LOGIN_USER);

        userService.updatePassword(userId.getEmail(), dto.getOldPassword(), dto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateProfile(
            HttpSession httpSession,
            @RequestBody UpdateProfileRequestDto dto
    ) {

        UserResponseDto userId = (UserResponseDto) httpSession.getAttribute(Const.LOGIN_USER);

        userService.updateProfile(userId.getEmail(), dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/withdraw")
    public ResponseEntity<Void> updateWithdraw(
            HttpSession httpSession,
            @RequestBody UpdateWithdrawRequestDto dto
    ) {

        UserResponseDto userId = (UserResponseDto) httpSession.getAttribute(Const.LOGIN_USER);

        userService.updateWithdraw(userId.getEmail(), dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
