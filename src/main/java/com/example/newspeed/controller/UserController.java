package com.example.newspeed.controller;

import com.example.newspeed.dto.UpdatePasswordRequestDto;
import com.example.newspeed.dto.UpdateProfileRequestDto;
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

        User userId = (User) httpSession.getAttribute("loginUser");

        UserResponseDto dto = userService.findByEmail(userId.getUserEmail());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/updatepassword")
    public ResponseEntity<Void> updatePassword(
            HttpSession httpSession,
            @RequestBody UpdatePasswordRequestDto dto
            ) {

        User userId = (User) httpSession.getAttribute("loginUser");

        userService.updatePassword(userId.getUserEmail(), dto.getOldPassword(), dto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateProfile(
            HttpSession httpSession,
            @RequestBody UpdateProfileRequestDto dto
    ) {

        User userId = (User) httpSession.getAttribute("loginUser");

        userService.updateProfile(userId.getUserEmail(), dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
