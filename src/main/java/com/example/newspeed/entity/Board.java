package com.example.newspeed.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="Board")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;


    @Column
    private String img_add;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Board(String title, String contents, String img_add) {
        this.title = title;
        this.contents = contents;
        this.img_add = img_add;
    }
    public Board() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateTitleOrContents(String title,String contents) {
        this.title = title;
        this.contents=contents;
    }
}

