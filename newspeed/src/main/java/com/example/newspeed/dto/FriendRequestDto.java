package com.example.newspeed.dto;

import lombok.Getter;

@Getter
public class FriendRequestDto {

    private String toFriendId;
    private String fromFriendId;
    private int status;

    public FriendRequestDto(String toFriendId, String fromFriendId, int status) {
        this.toFriendId = toFriendId;
        this.fromFriendId = fromFriendId;
        this.status = status;
    }
}
