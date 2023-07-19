package com.example.gradesubmission.service;

import java.util.List;
import java.util.Set;


import com.example.gradesubmission.entity.Student;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Student student, Long studentId);
    void deleteStudent(Long id);
    List<Student> getStudents();

    
    
}
