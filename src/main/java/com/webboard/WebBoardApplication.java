package com.webboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class WebBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBoardApplication.class, args);
	}

}
