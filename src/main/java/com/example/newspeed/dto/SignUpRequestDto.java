package com.example.newspeed.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private final String userEmail;
    private final String password;

    private final String userName;
    private final Integer age;
    private final String interests;

    public SignUpRequestDto(String userEmail, String password, String userName, Integer age, String interests) {
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
        this.age = age;
        this.interests = interests;
    }
}
