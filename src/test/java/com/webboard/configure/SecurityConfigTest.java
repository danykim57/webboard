package com.webboard.configure;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {
    @Test
    public void encodeTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "1234";
        String encodedPasswd;

        encodedPasswd = passwordEncoder.encode(password);
        System.out.println(encodedPasswd);
        assertNotEquals(password, encodedPasswd);
    }
}