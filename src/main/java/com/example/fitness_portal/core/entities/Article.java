package com.example.fitness_portal.core.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Article {
    @Column
    String category;
    @OneToMany
    List<Rating> ratings;
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    @Column(columnDefinition = "TEXT", length = 10000)
    private String articleText;
    @ManyToOne
    private AppUser author;
    @Column
    String pictureLink;
    @Column
    String title;
}
