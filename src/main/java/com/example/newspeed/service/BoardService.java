package com.example.newspeed.service;

import com.example.newspeed.dto.BoardResponseDto;
import com.example.newspeed.dto.UserResponseDto;
import com.example.newspeed.entity.Board;
import com.example.newspeed.entity.User;
import com.example.newspeed.repository.BoardRepository;
import com.example.newspeed.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponseDto save(String title, String contents,String img_add, String email){
        User finduser = userRepository.findByUserEmailOrElseThrow(email);
        Board board = new Board(title, contents, img_add,email);

        board.setUser(finduser);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContents(), savedBoard.getImg_add(), savedBoard.getEmail());
    }
    public BoardResponseDto findById(long id){

        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        return new BoardResponseDto(findBoard.getId(), findBoard.getTitle(), findBoard.getContents(), findBoard.getImg_add(), findBoard.getEmail());
    }
    public List<BoardResponseDto> findAll(){
        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();

    }
    @Transactional
    public void UpdateTitleOrContentsRequestDto(Long id, String title, String contents, UserResponseDto loginUser) {

        Board findboard = boardRepository.findByIdOrElseThrow(id);

        if(!findboard.getUser().getUserEmail().equals(loginUser.getEmail())){
            log.info("글쓴이가 아닙니다.");
            throw new RuntimeException("글쓴이가 아닙니다.");
        }
        findboard.updateTitleOrContents(title,contents);
    }

    public void delete(Long id,UserResponseDto loginUser){
        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        if(!findBoard.getUser().getUserEmail().equals(loginUser.getEmail())){
            log.info("글쓴이가 아닙니다.");
            throw new RuntimeException("글쓴이가 아닙니다.");
        }
        boardRepository.delete(findBoard);
    }
    public Page<BoardResponseDto> getPostsPage(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);

        return boardRepository.findAllByOrderByCreatedAtDesc(pageable).map(BoardResponseDto::toDto);
    }
}
