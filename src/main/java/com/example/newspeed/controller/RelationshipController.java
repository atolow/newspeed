package com.example.newspeed.controller;

import com.example.newspeed.dto.SignUpRequestDto;
import com.example.newspeed.dto.SignUpResponseDto;
import com.example.newspeed.dto.ToFriendRequestDto;
import com.example.newspeed.dto.ToFriendResponseDto;
import com.example.newspeed.service.RelationshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RelationshipController {

    private final RelationshipService relationshipService;

    public RelationshipController(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @PostMapping()
    public ResponseEntity<ToFriendResponseDto> createUser(@RequestBody ToFriendRequestDto requestdto) {
        ToFriendResponseDto responseDto = relationshipService.sendRelationship(requestdto);
        return new ResponseEntity<>(responseDto,  HttpStatus.CREATED);
    }

}
