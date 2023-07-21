package com.example.gradesubmission.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradesubmission.entity.Grade;
import com.example.gradesubmission.service.GradeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {
    
    private GradeService gradeService;

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> createGrade(@Valid @RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId){
        return new ResponseEntity<>(gradeService.createGrade(grade, studentId, courseId) , HttpStatus.CREATED);
    }
    
    @GetMapping("/{gradeId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long gradeId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
