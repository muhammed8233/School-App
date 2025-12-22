package com.example.School_App.schoolApp.controller;

import com.example.School_App.schoolApp.dto.EnrollmentDto;
import com.example.School_App.schoolApp.services.EnrollmentServiceInterface;
import com.example.School_App.schoolApp.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/enrollment")
@Validated
public class EnrollmentController {

    @Autowired private final EnrollmentServiceInterface enrollmentServiceInterface;

    public EnrollmentController(EnrollmentServiceInterface enrollmentServiceInterface, GradeService gradeService){
        this.enrollmentServiceInterface = enrollmentServiceInterface;
    }


    @PostMapping
    public ResponseEntity<String> enrollStudentInACourse(@RequestBody EnrollmentDto enrollmentDto){
         enrollmentServiceInterface.enrollStudentInCourse(enrollmentDto.getStudentId(),
                enrollmentDto.getCourseId());
         return ResponseEntity.ok("student enrolled successfully");
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDto>> getAllStudentInACourse(@PathVariable Long courseId ) {
        List<EnrollmentDto> savedEnrollment = enrollmentServiceInterface.getStudentsByACourse(courseId);

        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDto>> getAllCourseOfAStudent(@PathVariable Long studentId){
        List<EnrollmentDto> savedEnrollment = enrollmentServiceInterface.getCourseByStudent(studentId);

        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }

    @GetMapping("/getAllEnrollment")
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollment(){
        List<EnrollmentDto> savedEnrollment = enrollmentServiceInterface.getAllEnrollment();

        return ResponseEntity.ok(savedEnrollment);
    }
}
