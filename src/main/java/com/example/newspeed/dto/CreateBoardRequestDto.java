package com.example.newspeed.dto;

import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private String title;

    private String contents;

    private String img_add;
    private String email;


    public CreateBoardRequestDto(String title, String contents, String img_add, String email) {
        this.title = title;
        this.contents = contents;
        this.img_add = img_add;
        this.email = email;
    }

    public CreateBoardRequestDto() {
    }
}
