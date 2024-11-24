package com.example.newspeed.controller;

import com.example.newspeed.dto.BoardResponseDto;
import com.example.newspeed.dto.FriendRequestDto;
import com.example.newspeed.dto.FriendResponseDto;
import com.example.newspeed.dto.UserResponseDto;
import com.example.newspeed.service.BoardService;
import com.example.newspeed.service.RelationshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RelationshipController {

    private final RelationshipService relationshipService;
    private final BoardService boardService;


    public RelationshipController(RelationshipService relationshipService, BoardService boardService) {
        this.relationshipService = relationshipService;
        this.boardService = boardService;
    }

    @PostMapping("/sendRelationship")
    public ResponseEntity<FriendResponseDto> sendRelationship(@RequestBody FriendRequestDto requestDto) {

        FriendResponseDto responseDto = relationshipService.sendRelationship(requestDto.getToFriendId(), requestDto.getFromFriendId(),requestDto.getStatus());

        return new ResponseEntity<>(responseDto,  HttpStatus.CREATED);
    }

    @PatchMapping("/{rId}")
    public ResponseEntity<FriendResponseDto> acceptRelationship(
            @PathVariable(value = "rId") Long rId,
            @RequestBody FriendRequestDto requestDto) {

        relationshipService.acceptRelationship(rId, requestDto.getStatus());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}