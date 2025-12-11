package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;

import java.util.List;

public interface StudentServiceInt { ;

    void addNewStudent(StudentDto studentDTO);

    List<StudentDto> getStudents();
}
