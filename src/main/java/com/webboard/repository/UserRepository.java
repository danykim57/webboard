package com.webboard.repository;

import com.webboard.model.ConnectedUser;
import com.webboard.model.User;
import com.webboard.model.commons.Id;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User insert(User user);

    void update(User user);

    Optional<User> findById(Id<User, Long> userId);
}
