package com.example.newspeed.dto;

import lombok.Getter;

@Getter
public class FriendResponseDto {

    private String toFriendId;
    private String fromFriendId;
    private int status;

    public FriendResponseDto(String toFriendId, String fromFriendId, int status) {
        this.toFriendId = toFriendId;
        this.fromFriendId = fromFriendId;
        this.status = status;
    }
}
