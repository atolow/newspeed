package com.example.newspeed.repository;

import com.example.newspeed.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long id) {

        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다."));
    }


    Optional <User> findByUserEmail(String userEmail);
    default User findByUserEmailOrElseThrow(String userEmail) {
        return findByUserEmail(userEmail).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
