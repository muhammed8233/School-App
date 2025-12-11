package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import com.example.School_App.SchoolApp.Services.EnrollmentService;
import com.example.School_App.SchoolApp.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public void enrollStudentInACourse(@RequestBody EnrollmentRequest enrollmentRequest){
         enrollmentService.enrollStudentInCourse(enrollmentRequest.getStudentId(), enrollmentRequest.getCourseId());
    }
    @GetMapping("{courseId}")
    public List<StudentDto> getAllStudentInACourse(@PathVariable Long courseId ){
        return enrollmentService.getStudentsByACourse(courseId);
    }

    @GetMapping("{studentId}")
    public List<CourseRequest> getAllCourseOfAStudent(@PathVariable Long studentId){
        return enrollmentService.getCourseByStudent(studentId);
    }

}
