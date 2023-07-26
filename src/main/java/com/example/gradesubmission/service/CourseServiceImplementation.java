package com.example.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.gradesubmission.entity.Course;
import com.example.gradesubmission.entity.Student;
import com.example.gradesubmission.exception.EntityNotFoundException;
import com.example.gradesubmission.repository.CourseRepository;
import com.example.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    @Override
    public Course getCourse(Long courseId) {
        Optional<Course> wrappedCourse = courseRepository.findById(courseId);
        Course unwrappedCourse = unwrapCourse(wrappedCourse, courseId);
        return unwrappedCourse;
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course, Long courseId) {
        Course unwrappedCourse = getCourse(courseId);
        return update(course, unwrappedCourse);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Optional<Course> wrappedCourse = courseRepository.findById(courseId);
        courseRepository.delete(unwrapCourse(wrappedCourse, courseId));
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public static Course unwrapCourse(Optional<Course> wrappedCourse, Long courseId) {
        if (wrappedCourse.isPresent()) {
            return wrappedCourse.get();
        } else {
            throw new EntityNotFoundException(courseId, Course.class);
        }
    }

    public Course update(Course course, Course unwrappedCourse) {
        if (course.getSubject() != null) {
            unwrappedCourse.setSubject(course.getSubject());
        }
        if (course.getDescription() != null) {
            unwrappedCourse.setDescription(course.getDescription());
            ;
        }
        if (course.getCode() != null) {
            unwrappedCourse.setCode(course.getCode());
            ;
        }
        return unwrappedCourse;
    }

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course unwrappedCourse = getCourse(courseId);
        Optional<Student> wrappedStudent = studentRepository.findById(studentId);
        Student unwrappedStudent = StudentServiceImplementation.unwrapStudent(wrappedStudent, studentId);
        unwrappedCourse.getStudents().add(unwrappedStudent);
        courseRepository.save(unwrappedCourse);
        return unwrappedCourse;
    }

    @Override
    public Set<Student> getEnrolledStudents(Long courseId) {
        Course course = getCourse(courseId);
        return course.getStudents();
    }

}
