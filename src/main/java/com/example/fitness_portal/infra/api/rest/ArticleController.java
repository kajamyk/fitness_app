package com.example.fitness_portal.infra.api.rest;

import com.example.fitness_portal.infra.api.services.ArticleData;
import com.example.fitness_portal.infra.api.services.ArticleService;
import com.example.fitness_portal.infra.api.services.RatingService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    RatingService ratingService;

    @GetMapping("/")
    public ResponseEntity getAllArticles() {
        try {
            Map<String, List<ArticleData>> response = new HashMap<>();
            response.put("articles", articleService.getAllArticles());
            return ResponseEntity.ok(response);
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/article/{id}")
    public ResponseEntity getArticleById(@PathVariable Long id) {
        try {
            Map<String, ArticleData> response = new HashMap<>();
            response.put("article", articleService.getArticleById(id));
            return ResponseEntity.ok(response);
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity getArticlesByCategory(@PathVariable String category) {
        try {
            Map<String, List<ArticleData>> response = new HashMap<>();
            response.put("articles", articleService.getAllArticlesByCategory(category));
            return ResponseEntity.ok(response);
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PostMapping("/rating/{articleId}")
    public ResponseEntity addRating(@PathVariable Long articleId, @RequestBody ArticleController.RatingData ratingData) {
        try {
            articleService.addRating(articleId, ratingData);
            return ResponseEntity.ok(new ArrayList<>());
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @Data
    public static class RatingData {
        int rating;
    }
}
