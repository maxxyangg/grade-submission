package com.example.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;


import com.example.gradesubmission.entity.Student;
import com.example.gradesubmission.exception.EntityNotFoundException;
import com.example.gradesubmission.repository.StudentRepository;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImplementation implements StudentService{

    private StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        Optional<Student> wrappedStudent = studentRepository.findById(id);
        Student unwrappedStudent = unwrapStudent(wrappedStudent, id);
        return unwrappedStudent;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);

    }

    @Override
    public Student updateStudent(Student student, Long id){
        Student unwrappedStudent = getStudent(id);
        Student updatedStudent = update(student, unwrappedStudent);
        saveStudent(updatedStudent);
        return updatedStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudent(id);
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public static Student unwrapStudent(Optional<Student> wrappedOptional, Long id){
        if(wrappedOptional.isPresent()){
            return wrappedOptional.get();
        }else{
            throw new EntityNotFoundException(id, Student.class);
        }
    }

    public Student update(Student student, Student unwrappedStudent){
        if(student.getBirthDate() != null){
            unwrappedStudent.setBirthDate(student.getBirthDate());
        }
        if(student.getFirstName() != null){
            unwrappedStudent.setFirstName(student.getFirstName());
        }
        if(student.getLastName() != null){
            unwrappedStudent.setLastName(student.getLastName());
        }
        return unwrappedStudent;
    }

    
}
