package com.webboard.repository;

import com.webboard.controller.UserDTO;
import com.webboard.model.Email;
import com.webboard.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getUsers();

    User findByEmail(Email email);

    Optional<User> getUser(long id);

    long join(UserDTO userDTO);
}
