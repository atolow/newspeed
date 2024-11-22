package com.example.newspeed.controller;

import com.example.newspeed.common.Const;
import com.example.newspeed.dto.BoardResponseDto;
import com.example.newspeed.dto.CreateBoardRequestDto;
import com.example.newspeed.dto.UpdateTitleOrContentsRequestDto;
import com.example.newspeed.dto.UserResponseDto;
import com.example.newspeed.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
                        requestDto.getImg_add(),
                        requestDto.getEmail()
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
            @RequestBody UpdateTitleOrContentsRequestDto requestDto,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);


        boardService.UpdateTitleOrContentsRequestDto(id, requestDto.getTitle(), requestDto.getContents(), loginUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestBody BoardResponseDto responseDto,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        boardService.delete(id,loginUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/posts")
    public Page<BoardResponseDto> getPostsPage(@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo){
        return boardService.getPostsPage(pageNo);
    }
}




