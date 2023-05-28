package com.example.fitness_portal.core.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ForumPost {
    @Column
    String category;
    @OneToMany
    List<Comment> comments = new ArrayList<>();
    @Id
    @Column(name = "forum_post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumPostId;
    @Column(columnDefinition = "TEXT")
    private String forumPostText;

    @Column(columnDefinition = "TEXT")
    private String forumPostTitle;
    @ManyToOne
    private AppUser author;

    public ForumPost(String forumPostText, String forumPostTitle, String category, AppUser author) {
        this.comments = new ArrayList<>();
        this.forumPostTitle = forumPostTitle;
        this.forumPostText = forumPostText;
        this.category = category;
        this.author = author;
    }

    public ForumPost() {

    }
}
