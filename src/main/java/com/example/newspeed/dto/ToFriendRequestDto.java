package com.example.newspeed.dto;

public class ToFriendRequestDto {

    private String toFriendId;
    private String fromFriendId;

    public ToFriendRequestDto(String toFriendId, String fromFriendId) {
        this.toFriendId = toFriendId;
        this.fromFriendId = fromFriendId;
    }
}
