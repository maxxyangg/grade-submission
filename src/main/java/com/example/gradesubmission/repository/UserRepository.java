package com.example.gradesubmission.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.gradesubmission.entity.User;


public interface UserRepository extends CrudRepository<User, Long>{
    
}
