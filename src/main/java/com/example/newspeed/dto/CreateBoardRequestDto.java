package com.example.newspeed.dto;

import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private String title;

    private String contents;

    private String img_add;

    public CreateBoardRequestDto(String title, String contents, String img_add) {
        this.title = title;
        this.contents = contents;
        this.img_add = img_add;
    }
    public CreateBoardRequestDto() {
    }
}
