package com.example.newspeed.controller;

import com.example.newspeed.dto.BoardResponseDto;
import com.example.newspeed.dto.CreateBoardRequestDto;
import com.example.newspeed.dto.UpdateTitleOrContentsRequestDto;
import com.example.newspeed.dto.UserResponseDto;
import com.example.newspeed.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/newspeeds")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<BoardResponseDto> save(@RequestBody CreateBoardRequestDto requestDto) {

        BoardResponseDto boardResponseDto =
                boardService.save(
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getImg_add()
                );

        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathVariable Long id){
        BoardResponseDto boardResponseDto = boardService.findById(id);

        return new ResponseEntity<>(boardResponseDto,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll(){
        List<BoardResponseDto> boardResponseDtoList = boardService.findAll();
        return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateTitleOrContents(
            @PathVariable(value = "id") Long id,
            @RequestBody UpdateTitleOrContentsRequestDto requestDto) {

        BoardResponseDto boardResponseDto = boardService.updateTitleOrContents(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(boardResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boardService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}




