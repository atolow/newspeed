package com.example.newspeed.dto;

import lombok.Getter;

@Getter
public class AcceptFriendResponseDto {
    Long rId;
    int status;
    public AcceptFriendResponseDto(Long rId, int status) {
        this.rId = rId;
        this.status = status;
    }
}
