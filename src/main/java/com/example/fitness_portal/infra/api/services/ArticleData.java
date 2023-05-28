package com.example.fitness_portal.infra.api.services;

import lombok.Data;

@Data
public class ArticleData {
    Long id;
    int rating;
    String title;
    String author;
    String pictureLink;
    String text;
}
