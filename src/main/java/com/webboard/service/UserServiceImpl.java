package com.webboard.service;

import com.webboard.controller.UserDTO;
import com.webboard.model.User;
import com.webboard.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public Optional<User> getUser(long id) {
        return userRepository.getUser(id);
    }

    public long join(UserDTO userDTO) {
        User user = userDTO.getEntity();
        return userRepository.join(userDTO);
    }
}
