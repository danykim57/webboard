package com.webboard.controller;

import com.webboard.model.Post;
import com.webboard.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class PostController {
    Logger log = Logger.getLogger(this.getClass().getName());

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/jsp/")
    public String getJspIndex() {
//        List<Post> list = postService.findAll();
//        model.addAttribute("list" ,list);
        return "index.jsp";
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        List<Post> list = postService.findAll();
        model.addAttribute("list" ,list);
        return "index.html";
    }

    @GetMapping("/newpost")
    public String getNewPost(Model model, HttpServletRequest request) {
        model.addAttribute("PostDto", new PostDto());
        return "newpost.html";
    }

    @PostMapping("/newpost")
    public ModelAndView write(@ModelAttribute("post") @Valid final PostDto postDto, final HttpServletRequest request, final Errors errors) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        postService.write(new Post(null, postDto.getTitle(), postDto.getContent(), postDto.getWriter()));
        return modelAndView;
    }

    @PostMapping("/modifypost")
    public ModelAndView modify(@ModelAttribute("post") @Valid final PostDto postDto, final HttpServletRequest request, final Errors errors) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        postService.modify(new Post(null, postDto.getTitle(), postDto.getContent(), postDto.getWriter()));
        return modelAndView;
    }
}
