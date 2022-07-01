package com.webboard.controller;

import com.webboard.model.Email;
import com.webboard.model.User;

public class UserDTO {
    private Email email;
    private String password;

    public UserDTO(Email email, String password) {
        this.email = email;
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User getEntity() {
        return new User.Builder(this.email, this.password).build();
    }
}
