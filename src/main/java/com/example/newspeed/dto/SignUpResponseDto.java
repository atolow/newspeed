package com.example.newspeed.dto;

import com.example.newspeed.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;
    private final String userEmail;
    private String userName;
    private Integer age;
    private String interests;

    public SignUpResponseDto(Long id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }

    public SignUpResponseDto(Long id, String userEmail, String userName, Integer age, String interests) {
        this.id = id;
        this.userEmail = userEmail;
        this.userName = userName;
        this.age = age;
        this.interests = interests;
    }
    public SignUpResponseDto(User user) {
        this.id = user.getId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.age = user.getAge();
        this.interests = user.getInterests();
    }
}
