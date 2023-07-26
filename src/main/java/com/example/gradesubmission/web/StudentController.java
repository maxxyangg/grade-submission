package com.example.gradesubmission.web;

import java.util.List;


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

import com.example.gradesubmission.entity.Student;
import com.example.gradesubmission.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        return new ResponseEntity<Student>(studentService.getStudent(studentId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long studentId) {
        return new ResponseEntity<>(studentService.updateStudent(student, studentId) , HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }
}
