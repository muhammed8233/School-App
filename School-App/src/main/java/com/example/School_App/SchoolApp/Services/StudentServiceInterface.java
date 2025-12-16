package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StudentServiceInterface { ;

    Student addNewStudent(StudentDto studentDTO);

    List<StudentDto> getStudents();

    @Transactional
    List<Student> saveAllStudents(List<StudentDto> studentDto);

    Student getStudentById(Long studentId);
}
