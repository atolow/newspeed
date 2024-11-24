package com.example.newspeed.controller;

import com.example.newspeed.dto.SignUpRequestDto;
import com.example.newspeed.dto.SignUpResponseDto;
import com.example.newspeed.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<SignUpResponseDto> createUser(
            @RequestBody SignUpRequestDto requestdto) {
        SignUpResponseDto responseDto = userService.createUser(requestdto);
        return new ResponseEntity<>(responseDto,  HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SignUpResponseDto>> searchAllUser(){
        List<SignUpResponseDto> responseDtos = userService.searchAllUser();
        return new ResponseEntity<>(responseDtos,HttpStatus.OK);
    }

}
