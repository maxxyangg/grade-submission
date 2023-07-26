package com.example.gradesubmission.service;

import java.util.List;

import com.example.gradesubmission.entity.Grade;

public interface GradeService {

    Grade createGrade(Grade grade, Long studentId, Long courseId);

    Grade getGrade(Long studentId, Long courseId);

    Grade updateGrade(Long studentId, Long courseId, String score);

    Grade deleteGrade(Long studentId, Long courseId);

    List<Grade> getStudentGrades(Long studentId);

    List<Grade> getCourseGrades(Long courseId);

    List<Grade> getGrades();

}
