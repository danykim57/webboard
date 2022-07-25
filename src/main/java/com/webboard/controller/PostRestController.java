package com.webboard.controller;

import com.webboard.model.Post;
import com.webboard.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "api/board", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getList() throws Exception {
        List<Post> posts = postService.findAll();
        String str = "";
        for (Post post : posts) {
            str += post.toString();
        }
        return str;
    }
}
