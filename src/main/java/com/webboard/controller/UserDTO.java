package com.webboard.controller;

import com.webboard.validation.PasswordMatches;
import com.webboard.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDTO {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
