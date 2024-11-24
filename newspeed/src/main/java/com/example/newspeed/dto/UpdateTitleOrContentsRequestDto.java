package com.example.newspeed.dto;

import lombok.Getter;

@Getter
public class UpdateTitleOrContentsRequestDto {

    private String title;

    private String contents;

    private String img_add;

    public UpdateTitleOrContentsRequestDto(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public UpdateTitleOrContentsRequestDto() {
    }
}
