package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import com.example.School_App.SchoolApp.Services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "student")
public class StudentController {
    @Autowired
    private final StudentServiceInterface studentServiceInterface;

    public StudentController(StudentServiceInterface studentServiceInterface){
        this.studentServiceInterface = studentServiceInterface;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentServiceInterface.getStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody StudentDto  studentDto){
        studentServiceInterface.addNewStudent(studentDto);
    }
}
