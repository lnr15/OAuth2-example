package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication

public class OAuth2ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2ExampleApplication.class, args);
	}

}
