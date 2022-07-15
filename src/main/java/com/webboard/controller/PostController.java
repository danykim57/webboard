package com.webboard.controller;

import com.webboard.model.Post;
import com.webboard.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String getIndex() {
//        List<Post> list = postService.findAll();
//        model.addAttribute("list" ,list);
        return "index.jsp";
    }

    @GetMapping("/newpost")
    public String getNewPost(Model model, HttpServletRequest request) {
        model.addAttribute("PostDto", new PostDto());
        return "newpost.html";
    }

    @PostMapping("/newpost")
    public ModelAndView saveNewPost(@ModelAttribute PostDto postDto, HttpServletRequest request) {

        return new ModelAndView("saveNewPostSuccess");
    }
}
