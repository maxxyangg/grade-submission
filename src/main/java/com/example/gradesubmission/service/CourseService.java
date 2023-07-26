package com.example.gradesubmission.service;

import java.util.List;
import java.util.Set;

import com.example.gradesubmission.entity.Course;
import com.example.gradesubmission.entity.Student;

public interface CourseService {
    Course getCourse(Long courseId);

    Course saveCourse(Course course);

    Course updateCourse(Course course, Long courseId);

    void deleteCourse(Long courseId);

    Course addStudentToCourse(Long studentId, Long courseId);

    List<Course> getCourses();

    Set<Student> getEnrolledStudents(Long courseId);
}
