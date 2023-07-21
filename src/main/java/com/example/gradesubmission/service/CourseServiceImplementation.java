package com.example.gradesubmission.service;

import java.util.Optional;


import org.springframework.stereotype.Service;


import com.example.gradesubmission.entity.Course;
import com.example.gradesubmission.exception.EntityNotFoundException;
import com.example.gradesubmission.repository.CourseRepository;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private CourseRepository courseRepository;

    @Override
    public Course getCourse(Long courseId) {
        Optional<Course> wrappedCourse = courseRepository.findById(courseId);
        Course unwrappedCourse = unwrapCourse(wrappedCourse, courseId);
        return unwrappedCourse;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course,Long courseId) {
        Optional<Course> wrappedCourse = courseRepository.findById(courseId);
        Course unwrappedCourse = unwrapCourse(wrappedCourse, courseId);
        return update(course, unwrappedCourse);
    }

    @Override
    public void deleteCourse(Long courseId){
        Optional<Course> wrappedCourse = courseRepository.findById(courseId);
        courseRepository.delete(unwrapCourse(wrappedCourse, courseId));
    }

    public static Course unwrapCourse(Optional<Course> wrappedCourse, Long courseId) {
        if (wrappedCourse.isPresent()) {
            return wrappedCourse.get();
        } else {
            throw new EntityNotFoundException(courseId, Course.class);
        }
    }

    public Course update(Course course, Course unwrappedCourse){
        if(course.getSubject() != null){
            unwrappedCourse.setSubject(course.getSubject());
        }
        if(course.getDescription() != null){
            unwrappedCourse.setDescription(course.getDescription());;
        }
        if(course.getCode() != null){
            unwrappedCourse.setCode(course.getCode());;
        }
        return unwrappedCourse;
    }
}
