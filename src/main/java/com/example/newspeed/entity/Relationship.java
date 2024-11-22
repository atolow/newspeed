package com.example.newspeed.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Entity
@Table(name="relationship")
public class Relationship extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rId;

    @NonNull
    @NotBlank
    private String toFriendEmail;

    @NonNull
    @NotBlank
    private String fromFriendEmail;

    private int status;
}
