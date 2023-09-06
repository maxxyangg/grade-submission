package com.example.gradesubmission.service;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gradesubmission.entity.User;
import com.example.gradesubmission.exception.EntityNotFoundException;
import com.example.gradesubmission.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    @Override
    public User getUser(Long userId) {
        Optional<User> wrappedOptional = userRepository.findById(userId);
        return unwrapUser(wrappedOptional, userId);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        Optional<User> optional = userRepository.findById(userId);
        User unwrappedUser  = unwrapUser(optional, userId);
        return update(unwrappedUser, user);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        User user = unwrapUser(optional, userId);
        userRepository.delete(user);
    }

    public User update(User unwrappedUser, User user){
        if(user.getUsername() != null){
            unwrappedUser.setUsername(user.getUsername());
        }
        if(user.getPassword() != null){
            unwrappedUser.setPassword(user.getPassword());
        }
        return unwrappedUser;
    }

    public User unwrapUser(Optional<User> optional, Long userId){
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new EntityNotFoundException(userId, User.class);
        }
    }
    
}
