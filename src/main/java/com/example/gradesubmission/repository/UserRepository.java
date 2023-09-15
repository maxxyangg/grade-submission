package com.example.gradesubmission.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.gradesubmission.entity.User;


public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
