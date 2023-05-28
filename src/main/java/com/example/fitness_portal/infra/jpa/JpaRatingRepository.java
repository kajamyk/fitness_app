package com.example.fitness_portal.infra.jpa;

import com.example.fitness_portal.core.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRatingRepository extends JpaRepository<Rating, Long> {
}
