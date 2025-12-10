package com.example.School_App.Models.SchoolApp.Controller;

import com.example.School_App.Models.SchoolApp.Course;
import com.example.School_App.Models.SchoolApp.Enrollment;
import com.example.School_App.Models.SchoolApp.Services.EnrollmentService;
import com.example.School_App.Models.SchoolApp.Student;
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
    public Enrollment enrollStudentInACourse(@RequestBody Enrollment enrollment,
                                         @RequestParam Long studentId,
                                         @RequestParam Long courseId){
        return enrollmentService.enrollStudentInCourse(studentId,courseId,enrollment);
    }
    @GetMapping("{courseId}")
    public List<Student> getAllStudentInACourse(@PathVariable Long courseId){
        return enrollmentService.getStudentsByCourse(courseId);
    }

    @GetMapping("{studentId}")
    public List<Course> getAllCourseOfAStudent(@PathVariable Long studentId){
        return enrollmentService.getCourseByStudent(studentId);
    }

}
