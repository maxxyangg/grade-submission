package com.example.gradesubmission.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradesubmission.entity.Course;
import com.example.gradesubmission.entity.Student;
import com.example.gradesubmission.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
        return new ResponseEntity<>(courseService.getCourse(courseId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course, @PathVariable Long courseId) {
        return new ResponseEntity<>(courseService.updateCourse(course, courseId), HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.getEnrolledStudents(courseId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseService.getCourses() ,HttpStatus.OK);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId, courseId) , HttpStatus.OK);
    }

}
