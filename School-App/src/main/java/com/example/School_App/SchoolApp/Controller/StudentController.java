package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import com.example.School_App.SchoolApp.Services.StudentService;
import com.example.School_App.SchoolApp.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "student")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDto> getStudent(){
        return studentService.getStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody StudentDto  studentDto){
        studentService.addNewStudent(studentDto);
    }
}
