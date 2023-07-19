package com.example.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.gradesubmission.entity.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long>{
    
}
