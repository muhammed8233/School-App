package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
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
    public void enrollStudentInACourse(@RequestBody EnrollmentRequest enrollmentRequest){
         enrollmentServiceInterface.enrollStudentInCourse(enrollmentRequest.getStudentId(), enrollmentRequest.getCourseId());
    }
    @GetMapping("/{courseId}")
    public List<StudentDto> getAllStudentInACourse(@RequestParam Long courseId ){
        return enrollmentServiceInterface.getStudentsByACourse(courseId);
    }

    @GetMapping("/{studentId}")
    public List<CourseRequest> getAllCourseOfAStudent(@RequestParam Long studentId){
        return enrollmentServiceInterface.getCourseByStudent(studentId);
    }

}
