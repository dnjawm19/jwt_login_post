package com.example.jwt_login_post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JwtLoginPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtLoginPostApplication.class, args);
    }

}
