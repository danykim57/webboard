package com.webboard.service;

import com.webboard.controller.UserDTO;
import com.webboard.exceptions.EmailAlreadyExistException;
import com.webboard.model.Email;
import com.webboard.model.Role;
import com.webboard.model.User;
import com.webboard.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(final UserDTO userDto) {
        if (emailExist(userDto.getEmail())) {
            throw new EmailAlreadyExistException(" " + userDto.getEmail());
        }
        final User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        log.info("Name: " + user.getName() + " Email: " + user.getEmail() + " password: " + user.getPassword());

        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
