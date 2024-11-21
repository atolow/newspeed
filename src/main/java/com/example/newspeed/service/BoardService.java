package com.example.newspeed.service;

import com.example.newspeed.dto.BoardResponseDto;
import com.example.newspeed.entity.Board;
import com.example.newspeed.entity.User;
import com.example.newspeed.repository.BoardRepository;
import com.example.newspeed.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponseDto save(String title, String contents,String img_add){
        Board board = new Board(title, contents, img_add);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContents(), savedBoard.getImg_add());
    }
    public BoardResponseDto findById(long id){

        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        return new BoardResponseDto(findBoard.getId(), findBoard.getTitle(), findBoard.getContents(), findBoard.getImg_add());
    }
    public List<BoardResponseDto> findAll(){
        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();

    }
    @Transactional
    public BoardResponseDto updateTitleOrContents(Long id,String title, String contents) {

        Board findboard = boardRepository.findByIdOrElseThrow(id);


        findboard.updateTitleOrContents(title,contents);

        return new BoardResponseDto(findboard.getId(),findboard.getTitle(),findboard.getContents(), findboard.getImg_add());
    }

    public void delete(Long id){
        Board deleteBoard = boardRepository.findByIdOrElseThrow(id);
        boardRepository.delete(deleteBoard);
    }
}
