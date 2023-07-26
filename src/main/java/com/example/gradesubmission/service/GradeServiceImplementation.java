package com.example.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradesubmission.entity.Course;
import com.example.gradesubmission.entity.Grade;
import com.example.gradesubmission.entity.Student;
import com.example.gradesubmission.exception.GradeNotFoundException;
import com.example.gradesubmission.exception.StudentNotEnrolledException;
import com.example.gradesubmission.repository.CourseRepository;
import com.example.gradesubmission.repository.GradeRepository;
import com.example.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeServiceImplementation implements GradeService {

    private GradeRepository gradeRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Override
    public Grade createGrade(Grade grade, Long studentId, Long courseId) {
        Student student = StudentServiceImplementation.unwrapStudent(studentRepository.findById(studentId), studentId);
        Course course = CourseServiceImplementation.unwrapCourse(courseRepository.findById(courseId), courseId);

        if (!student.getCourses().contains(course)) {
            throw new StudentNotEnrolledException(studentId, courseId);
        }

        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> wrappedGrade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        Grade grade = unwrapGrade(wrappedGrade, studentId, courseId);
        return grade;
    }

    @Override
    public Grade updateGrade(Long studentId, Long courseId, String score) {
        Grade grade = getGrade(studentId, courseId);
        grade.setScore(score);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade deleteGrade(Long studentId, Long courseId) {
        Grade grade = getGrade(studentId, courseId);
        gradeRepository.delete(grade);
        return grade;
    }

    public static Grade unwrapGrade(Optional<Grade> wrappedGrade, Long studentId, Long courseId) {
        if (wrappedGrade.isPresent()) {
            return wrappedGrade.get();
        } else {
            throw new GradeNotFoundException(studentId, courseId);
        }
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getGrades() {
        return (List<Grade>) gradeRepository.findAll();

    }

}
