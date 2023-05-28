package com.example.fitness_portal.infra.api.services;

import com.example.fitness_portal.core.entities.AppUser;
import com.example.fitness_portal.core.entities.Article;
import com.example.fitness_portal.core.entities.Rating;
import com.example.fitness_portal.infra.api.rest.ArticleController;
import com.example.fitness_portal.infra.jpa.JpaArticleRepository;
import com.example.fitness_portal.infra.jpa.JpaRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    JpaArticleRepository articleRepository;

    @Autowired
    RatingService ratingService;

    @Autowired
    UserService userService;
    @Autowired
    private JpaRatingRepository jpaRatingRepository;

    private ArticleData mapArticle(Article article) {
        ArticleData articleData = new ArticleData();
        articleData.author = article.getAuthor().getUserName();
        articleData.id = article.getArticleId();
        articleData.pictureLink = article.getPictureLink();
        articleData.rating = ratingService.calculateRatings(article.getRatings());
        articleData.title = article.getTitle();
        articleData.text = article.getArticleText();
        return articleData;
    }

    private int getCurrentUserArticleRating(Long articleId) throws Exception {
        AppUser currentUser = userService.getCurrentUser();
        Article article = articleRepository.findById(articleId).get();
        Optional<Rating> rating = article
                .getRatings()
                .stream()
                .filter(articleRating -> articleRating.getRatingUser().equals(currentUser))
                .findFirst();

        return rating.map(Rating::getRatingValue).orElse(0);
    }

    public List<ArticleData> getAllArticles() {
        return articleRepository
                .findAll()
                .stream()
                .map(this::mapArticle)
                .toList();
    }

    public ArticleData getArticleById(Long id) throws Exception {
        ArticleData article =  articleRepository
                .findById(id)
                .stream()
                .map(this::mapArticle)
                .findFirst().get();
        article.setRating(getCurrentUserArticleRating(id));
        return article;
    }

    public List<ArticleData> getAllArticlesByCategory(String category) {
        return articleRepository
                .findAll()
                .stream()
                .filter(article -> article.getCategory().equals(category))
                .map(this::mapArticle)
                .toList();
    }

    public void addRating(Long articleId, ArticleController.RatingData ratingData) throws Exception {
        AppUser currentUser = userService.getCurrentUser();
        Article article = articleRepository.findById(articleId).get();
        Optional<Rating> optionalRating = article.getRatings()
                .stream()
                .filter(rating1 -> rating1.getRatingUser().equals(currentUser))
                .findFirst();
        if(optionalRating.isPresent()){
            optionalRating.get().setRatingValue(ratingData.getRating());
        }
        else {
            Rating rating = new Rating(currentUser, ratingData.getRating());
            ratingService.saveRating(rating);
            article.getRatings().add(rating);
        }
        articleRepository.save(article);
    }
}


