package com.webboard.service;

import com.webboard.controller.UserDTO;
import com.webboard.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRestService {
    List<User> getUsers();

    Optional<User> getUser(long id);

}
