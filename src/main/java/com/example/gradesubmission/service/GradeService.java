package com.example.gradesubmission.service;

import java.util.Optional;

import com.example.gradesubmission.entity.Grade;

public interface GradeService {
    
    Grade createGrade(Grade grade, Long studentId, Long courseId);

    
}
