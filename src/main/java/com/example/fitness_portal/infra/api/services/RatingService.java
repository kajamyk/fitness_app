package com.example.fitness_portal.infra.api.services;

import com.example.fitness_portal.core.entities.AppUser;
import com.example.fitness_portal.core.entities.Rating;
import com.example.fitness_portal.infra.api.rest.ArticleController;
import com.example.fitness_portal.infra.jpa.JpaRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    JpaRatingRepository ratingRepository;

    @Autowired
    UserService userService;

    public int calculateRatings(List<Rating> ratings) {
        int result = ratings
                .stream()
                .mapToInt(Rating::getRatingValue)
                .sum();
        return result != 0 ? Math.round(result /= ratings.size()) : result;
    }

    public void addRating(Long articleId, ArticleController.RatingData ratingData) throws Exception {
        AppUser currentUser = userService.getCurrentUser();
        Rating rating = new Rating(currentUser, ratingData.getRating());
        ratingRepository.save(rating);
    }

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }
}
