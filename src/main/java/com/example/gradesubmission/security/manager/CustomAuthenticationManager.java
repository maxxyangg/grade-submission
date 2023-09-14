package com.example.gradesubmission.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.stereotype.Component;

import com.example.gradesubmission.entity.User;
import com.example.gradesubmission.security.PasswordEncoder;
import com.example.gradesubmission.service.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager{

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUserByUsername(authentication.getName());
        if(!passwordEncoder.BCryptEncoder().matches(authentication.getCredentials().toString(), user.getPassword())){
            throw new BadCredentialsException("Username does not exist");
        }else {
            System.err.println(authentication.getName().toString() + " " + authentication.getCredentials().toString());
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), user.getPassword());
        }
    }
    
}
