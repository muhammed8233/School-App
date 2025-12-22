package com.example.school_app.schoolApp.services;

import com.example.school_app.schoolApp.model.Student;
import com.example.school_app.schoolApp.dto.StudentDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentServiceInterface { ;

    StudentDto addNewStudent(@Valid  StudentDto studentDTO);

    List<StudentDto> getStudents();

    @Transactional
    List<StudentDto> saveAllStudents(List<StudentDto> studentDto);

    Student getStudentById(Long studentId);
}
