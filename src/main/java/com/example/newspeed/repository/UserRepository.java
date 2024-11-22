package com.example.newspeed.repository;

import com.example.newspeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional <User> findByUserEmail(String userEmail);
    default User findByUserEmailOrElseThrow(String userEmail) {
        return findByUserEmail(userEmail).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
