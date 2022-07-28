package com.webboard.controller;

import com.webboard.model.Post;
import com.webboard.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "api/board", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getList() throws Exception {
        List<Post> posts = postService.findAll();
        String str = "";
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping(path = "post")
    public ResponseEntity posting(Post post) {
        Post newPost = postService.write(post);
        return ResponseEntity.ok().body(newPost);
    }


}
