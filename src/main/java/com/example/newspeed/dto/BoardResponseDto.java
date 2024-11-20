package com.example.newspeed.dto;

import com.example.newspeed.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private final Long id;

    private final String title;

    private final String contents;

    private final String img_add;

    public BoardResponseDto(Long id, String title, String contents, String img_add) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.img_add = img_add;
    }

    public static BoardResponseDto toDto(Board board){
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContents(), board.getImg_add());
    }
}