package com.example.newspeed.controller;

import com.example.newspeed.dto.UpdatePasswordRequestDto;
import com.example.newspeed.dto.UserResponseDto;
import com.example.newspeed.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto dto = userService.findById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable long id,
            @RequestBody @Valid UpdatePasswordRequestDto dto
            ) {

        userService.updatePassword(id, dto.getOldPassword(), dto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
