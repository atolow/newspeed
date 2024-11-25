package com.example.newspeed.dto;

import com.example.newspeed.entity.Board;
import lombok.Getter;

@Getter
public class UpdateTitleOrContentsDto {

    private String title;

    private String contents;

    private String email;

    public UpdateTitleOrContentsDto(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;
    }

    public UpdateTitleOrContentsDto(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public UpdateTitleOrContentsDto() {
    }
}
