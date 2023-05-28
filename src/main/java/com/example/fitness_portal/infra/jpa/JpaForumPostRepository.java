package com.example.fitness_portal.infra.jpa;

import com.example.fitness_portal.core.entities.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaForumPostRepository extends JpaRepository<ForumPost, Long> {
}
