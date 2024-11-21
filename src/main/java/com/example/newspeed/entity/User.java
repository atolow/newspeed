package com.example.newspeed.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Entity
@Table(name = "user")
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String userEmail;

    @NonNull
    @Column(nullable = false)
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
