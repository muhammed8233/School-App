package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import com.example.School_App.SchoolApp.Services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired private final StudentServiceInterface studentServiceInterface;

    public StudentController(StudentServiceInterface studentServiceInterface){
        this.studentServiceInterface = studentServiceInterface;
    }

    @GetMapping
    public List<StudentDto> getStudent(){
        return studentServiceInterface.getStudents();
    }

    @PostMapping
    public Student addNewStudent(@RequestBody StudentDto  studentDto){
        return studentServiceInterface.addNewStudent(studentDto);
    }

    @PostMapping("save")
    public List<StudentDto> uploadStudent(@RequestBody List<StudentDto> studentDtoS){
        return studentServiceInterface.saveAllStudents(studentDtoS);
    }
}
