package com.example.gradesubmission.service;

import com.example.gradesubmission.entity.Course;

public interface CourseService {
    Course getCourse(Long courseId);
    Course createCourse(Course course);
    Course updateCourse(Course course, Long courseId);
    
}
