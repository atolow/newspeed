package com.example.newspeed.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="relationship")
public class Relationship extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rId;

    private String toFriendId;

    private String fromFriendId;

    private int status;

    public Relationship(Long rId, String toFriendId, String fromFriendId, int status) {
        this.rId = rId;
        this.toFriendId = toFriendId;
        this.fromFriendId = fromFriendId;
        this.status = status;
    }
    public Relationship(){
    }

}