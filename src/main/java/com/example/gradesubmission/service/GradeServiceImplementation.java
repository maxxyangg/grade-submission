package com.example.gradesubmission.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradesubmission.entity.Course;
import com.example.gradesubmission.entity.Grade;
import com.example.gradesubmission.entity.Student;
import com.example.gradesubmission.repository.CourseRepository;
import com.example.gradesubmission.repository.GradeRepository;
import com.example.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
public class GradeServiceImplementation implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Grade createGrade(Grade grade, Long studentId, Long courseId) {

        Student student = StudentServiceImplementation.unwrapStudent(studentRepository.findById(studentId), studentId);
        Course course = CourseServiceImplementation.unwrapCourse(courseRepository.findById(courseId), courseId);
        // ToDo: Check if student and course exist. Exception handle
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    
}
