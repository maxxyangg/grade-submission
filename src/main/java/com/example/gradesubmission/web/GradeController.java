package com.example.gradesubmission.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradesubmission.entity.Grade;

@RestController
@RequestMapping("/grade")
public class GradeController {
    
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> createGrade(Grade grade, Long studentId, Long courseId){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("/{gradeId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long gradeId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
