package com.webboard.repository;

import com.webboard.controller.UserDTO;
import com.webboard.model.ConnectedUser;
import com.webboard.model.User;
import com.webboard.model.commons.Id;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getUsers();

    Optional<User> getUser(long id);

    long join(UserDTO userDTO);
}
