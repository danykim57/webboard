package com.webboard.service;

import com.webboard.model.Email;
import com.webboard.model.User;
import com.webboard.model.UserPrincipal;
import com.webboard.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //Out of circular ref error, replace constructor DI to setter DI
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        try {
            Email email = new Email(emailAddress);
            User user = userRepository.findByEmail(email);
            return new UserPrincipal(user);
        } catch (DataAccessException e) {
            logger.debug("Account not found", e);
            throw new UsernameNotFoundException("Email is not found");
        }
    }
}
