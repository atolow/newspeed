package com.example.newspeed.repository;

import com.example.newspeed.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

    default Relationship findByIdOrElseThrow(Long rId) {

        return findById(rId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist rId = " + rId));
    }
}