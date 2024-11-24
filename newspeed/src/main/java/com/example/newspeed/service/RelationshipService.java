package com.example.newspeed.service;

import com.example.newspeed.dto.AcceptFriendResponseDto;
import com.example.newspeed.dto.FriendResponseDto;
import com.example.newspeed.entity.Board;
import com.example.newspeed.entity.Relationship;
import com.example.newspeed.entity.User;
import com.example.newspeed.repository.RelationshipRepository;
import com.example.newspeed.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;
    private final UserRepository userRepository;

    public FriendResponseDto sendRelationship(String toFriendId, String fromFriendId, int status) {

        User toFriend = userRepository.findByUserEmailOrElseThrow(toFriendId);
        User FromFriend = userRepository.findByUserEmailOrElseThrow(fromFriendId);
        status = 1;

        return new FriendResponseDto(toFriend.getUserEmail(), FromFriend.getUserEmail(), status);
    }

    public AcceptFriendResponseDto acceptRelationship(Long rId, int status) {
        Relationship findRId = relationshipRepository.findByIdOrElseThrow(rId);
        status = 2;
        return new AcceptFriendResponseDto(findRId.getRId(), status);
    }

}