package com.example.gradesubmission.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    
    @Bean
    public BCryptPasswordEncoder BCryptEncoder() {
        return new BCryptPasswordEncoder();
    }
}
