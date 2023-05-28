package com.example.fitness_portal.infra.api.services;

import com.example.fitness_portal.core.entities.AppUser;
import com.example.fitness_portal.core.entities.Comment;
import com.example.fitness_portal.core.entities.ForumPost;
import com.example.fitness_portal.infra.api.rest.ForumPostController;
import com.example.fitness_portal.infra.jpa.JpaForumPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumPostService {
    @Autowired
    JpaForumPostRepository forumPostRepository;
    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    public List<ForumPost> getAllForumPosts() {
        return forumPostRepository.findAll();
    }

    public List<ForumPost> getForumPostByCategory(String category) {
        return getAllForumPosts()
                .stream()
                .filter(forumPost -> forumPost.getCategory().equals(category))
                .toList();
    }

    public ForumPost getForumPostById(Long id) {
        return forumPostRepository.findById(id).get();
    }

    public void addNewPost(ForumPostController.PostBody postBody) throws Exception {
        AppUser currentUser = userService.getCurrentUser();
        ForumPost forumPost = new ForumPost(postBody.getPostText(), postBody.getPostTitle(), postBody.getCategory(), currentUser);
        forumPostRepository.save(forumPost);
    }

    public void addComment(Long id, String text) throws Exception {
        AppUser currentUser = userService.getCurrentUser();
        ForumPost forumPost = forumPostRepository.findById(id).get();
        Comment comment = new Comment(currentUser, text);
        commentService.saveComment(comment);
        forumPost.getComments().add(comment);
        forumPostRepository.save(forumPost);
    }
}
