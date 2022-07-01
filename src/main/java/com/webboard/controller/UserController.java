package com.webboard.controller;

import com.webboard.model.Email;
import com.webboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
    //Check for Credentials
    @PostMapping("/login")
    public String login(@ModelAttribute(name="loginForm") UserDTO userDTO, Model m) {
        Email email = userDTO.getEmail();
        String passwd = userDTO.getPassword();
        if (email.equals("sa") && passwd.equals("1234")) {
            m.addAttribute("email", email);
            m.addAttribute("pass", passwd);
            return "welcome";
        }
        System.out.println(email);
        System.out.println(passwd);
        m.addAttribute("error", "Incorrect Username & Password");
        return "welcome";
    }
}
