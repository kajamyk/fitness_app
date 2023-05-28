package com.example.fitness_portal.infra.api.rest;

import com.example.fitness_portal.core.entities.ForumPost;
import com.example.fitness_portal.infra.api.services.ArticleData;
import com.example.fitness_portal.infra.api.services.ForumPostService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/forum")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ForumPostController {
    @Autowired
    ForumPostService forumPostService;

    @GetMapping("/{category}")
    public ResponseEntity getAllByCategory(@PathVariable String category) {
        try {
            Map<String, List<ForumPost>> response = new HashMap<>();
            response.put("posts", forumPostService.getForumPostByCategory(category));
            return ResponseEntity.ok(response);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/post/{id}")
    public ResponseEntity getPostById(@PathVariable Long id) {
        try {
            Map<String, ForumPost> response = new HashMap<>();
            response.put("post", forumPostService.getForumPostById(id));
            return ResponseEntity.ok(response);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/post/{id}/comment")
    public ResponseEntity addComment(@PathVariable Long id, @RequestBody PostComment comment) {
        try {
            forumPostService.addComment(id, comment.getPostComment());
            return ResponseEntity.ok(new ArrayList<>());
        }
        catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity addNewPost(@RequestBody @Valid PostBody postBody) {
        try {
            forumPostService.addNewPost(postBody);
            return ResponseEntity.ok(new ArrayList<>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @Data
    public static class PostBody {
        String postTitle;
        String postText;
        String category;
    }

    @Data
    public static class PostComment {
        String postComment;
    }
}
