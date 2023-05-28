package com.example.fitness_portal.core.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @OneToOne
    AppUser author;
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column
    private String commentText;

    public Comment(AppUser author, String commentText) {
        this.author = author;
        this.commentText = commentText;
    }

    public Comment() {

    }
}
