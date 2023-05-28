package com.example.fitness_portal.infra.jpa;

import com.example.fitness_portal.core.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}
