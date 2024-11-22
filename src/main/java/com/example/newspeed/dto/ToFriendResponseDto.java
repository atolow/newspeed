package com.example.newspeed.dto;

public class ToFriendResponseDto {
    private String toFriendId;
    private String fromFriendId;

    public ToFriendResponseDto(String toFriendId, String fromFriendId) {
        this.toFriendId = toFriendId;
        this.fromFriendId = fromFriendId;
    }
}
