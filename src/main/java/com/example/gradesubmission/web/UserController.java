package com.example.gradesubmission.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradesubmission.entity.User;
import com.example.gradesubmission.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        return new ResponseEntity<User>(userService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId){
        return new ResponseEntity<User>(userService.updateUser(userId, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

}
