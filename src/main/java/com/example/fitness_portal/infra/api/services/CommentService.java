package com.example.fitness_portal.infra.api.services;

import com.example.fitness_portal.core.entities.Comment;
import com.example.fitness_portal.infra.jpa.JpaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    JpaCommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
