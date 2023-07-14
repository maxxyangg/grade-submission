package com.example.gradesubmission.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Grade {

    private Long id;
    private String score;
    private Student student;
    private Course course;

    private String test;
    
}
