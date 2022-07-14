package com.webboard.controller;

import com.webboard.model.Post;
import com.webboard.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class PostController {
    Logger log = Logger.getLogger(this.getClass().getName());

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/")
    public String getIndex(Model model, HttpServletRequest request) {
        List<Post> list = postService.findAll();
        model.addAttribute("list" ,list);
        return "index"; }
}
