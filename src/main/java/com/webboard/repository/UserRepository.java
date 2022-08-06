package com.webboard.repository;

import com.webboard.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getUsers();

    User findByEmail(String email);

    Optional<User> getUser(long id);

    User save(User user);
}
