package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import com.example.School_App.SchoolApp.Services.EnrollmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "enrollment")
public class EnrollmentController {
    private final EnrollmentServiceInterface enrollmentServiceInterface;

    @Autowired
    public EnrollmentController(EnrollmentServiceInterface enrollmentServiceInterface){
        this.enrollmentServiceInterface = enrollmentServiceInterface;
    }

    @PostMapping
    public Enrollment enrollStudentInACourse(@RequestBody EnrollmentDto enrollmentDto){
         return enrollmentServiceInterface.enrollStudentInCourse(enrollmentDto.getStudentId(),
                 enrollmentDto.getCourseId());
    }
    @GetMapping("/{courseId}")
    public List<EnrollmentDto> getAllStudentInACourse(@RequestParam Long courseId ){
        return enrollmentServiceInterface.getStudentsByACourse(courseId);
    }

    @GetMapping("/{studentId}")
    public List<EnrollmentDto> getAllCourseOfAStudent(@RequestParam Long studentId,
                                                      @RequestParam Long courseId){
        return enrollmentServiceInterface.getCourseByStudent(studentId);
    }


}
