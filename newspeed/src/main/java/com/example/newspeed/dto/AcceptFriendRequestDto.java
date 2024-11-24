package com.example.newspeed.dto;

import lombok.Getter;

@Getter
public class AcceptFriendRequestDto {
    Long rId;
    int status;
    public AcceptFriendRequestDto(Long rId, int status) {
        this.rId = rId;
        this.status = status;
    }
}
