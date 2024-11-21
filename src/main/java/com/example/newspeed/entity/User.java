package com.example.newspeed.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Transactional
@Entity
@Table(name = "user")
@Getter
public class User extends BaseEntity {

    @Id
    @NonNull
    @Column(nullable = false, unique = true)
    @NotBlank
    private String userEmail;

    @NonNull
    @Column(nullable = false)
    @NotBlank
    private String password;

    private String userName;
    private Integer age;
    private String interests;

    protected User(){}

    @Builder
    public User(String userEmail, String password, String userName, Integer age, String interests) {
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
        this.age = age;
        this.interests = interests;
    }

    public void updatePassword(String password) {

        this.password = password;
    }
}
