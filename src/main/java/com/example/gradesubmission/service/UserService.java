package com.example.gradesubmission.service;



import com.example.gradesubmission.entity.User;

public interface UserService {
    User getUser(Long userId);

    User saveUser(User user);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);

    User getUserByUsername(String username);
}