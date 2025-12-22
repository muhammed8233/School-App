package com.example.School_App.schoolApp.services;

import com.example.School_App.schoolApp.model.Student;
import com.example.School_App.schoolApp.dto.StudentDto;
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
