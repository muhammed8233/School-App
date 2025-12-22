package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import com.example.School_App.SchoolApp.Services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
@Validated
public class StudentController {

    @Autowired private final StudentServiceInterface studentServiceInterface;

    public StudentController(StudentServiceInterface studentServiceInterface){
        this.studentServiceInterface = studentServiceInterface;
    }

    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody StudentDto studentDto) {
        studentServiceInterface.addNewStudent(studentDto);
        return ResponseEntity.ok("Student registered successfully");
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudent(){
       List<StudentDto> savedStudent = studentServiceInterface.getStudents();

        return ResponseEntity.ok(savedStudent);
    }

    @PostMapping("/bulk-register")
    public ResponseEntity<List<StudentDto>> registerMultipleStudents(@RequestBody List<StudentDto> dtos) {
        List<StudentDto> savedStudents = studentServiceInterface.saveAllStudents(dtos);
        return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
    }


}
