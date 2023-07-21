package com.example.gradesubmission.exception;

public class GradeNotFoundException extends RuntimeException{
    
    public GradeNotFoundException(Long studentId, Long courseId) {
        super("The student with id: '" + studentId + "' and course id: '" + courseId + "' does not exist.");
    }
}
