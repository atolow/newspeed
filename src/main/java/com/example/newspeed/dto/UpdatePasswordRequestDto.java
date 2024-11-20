package com.example.newspeed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePasswordRequestDto {

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*[@$!%*?&#])[A-Za-z\\\\d@$!%*?&#]$")
    @Size(min = 8, max = 20)
    private final String oldPassword;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*[@$!%*?&#])[A-Za-z\\\\d@$!%*?&#]$")
    @Size(min = 8, max = 20)
    private final String newPassword;
}
