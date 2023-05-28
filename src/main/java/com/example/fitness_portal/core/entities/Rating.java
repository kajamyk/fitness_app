package com.example.fitness_portal.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
public class Rating {
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    @Column
    @Max(5)
    @Min(1)
    private int ratingValue;
    @OneToOne
    private AppUser ratingUser;

    public Rating(AppUser ratingUser, int ratingValue) {
        this.ratingUser = ratingUser;
        this.ratingValue = ratingValue;
    }

    public Rating() {

    }
}
