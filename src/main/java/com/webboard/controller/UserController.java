package com.webboard.controller;

import com.webboard.exceptions.EmailAlreadyExistException;
import com.webboard.model.User;
import com.webboard.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/")
    public String getIndex() { return "index"; }
    @GetMapping("/signup")
    public String showSignUp(WebRequest webRequest, Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "sign-up";
    }

    @PostMapping("/signup")
    public ModelAndView signUp(@ModelAttribute("user") @Valid final UserDTO userDto, final HttpServletRequest request, final Errors errors) {
        log.debug("Registering user account with information: {}", userDto);

        try {
            final User user = userService.saveUser(userDto);

            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        } catch (final EmailAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("registration", "user", userDto);
            mav.addObject("message", "가입 실패");
            return mav;
        } catch (final RuntimeException ex) {
            log.warn("Unable to register user", ex);
            return new ModelAndView("emailError", "user", userDto);
        }
        return new ModelAndView("successRegister", "user", userDto);
    }

}
