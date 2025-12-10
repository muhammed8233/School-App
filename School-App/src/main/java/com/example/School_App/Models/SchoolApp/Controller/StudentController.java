package com.example.School_App.Models.SchoolApp.Controller;

import com.example.School_App.Models.SchoolApp.Services.StudentService;
import com.example.School_App.Models.SchoolApp.Student;
import org.hibernate.boot.internal.Abstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudent();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student  student){
        studentService.registerNewStudent(student);
    }
}
