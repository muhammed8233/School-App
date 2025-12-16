package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.SchoolAppDto.ScoreDto;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import com.example.School_App.SchoolApp.Services.EnrollmentServiceInterface;
import com.example.School_App.SchoolApp.Services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/enrollment")
public class EnrollmentController {

    @Autowired private final EnrollmentServiceInterface enrollmentServiceInterface;
    @Autowired private final GradeService gradeService; // Autowire GradeService here


    public EnrollmentController(EnrollmentServiceInterface enrollmentServiceInterface, GradeService gradeService){
        this.enrollmentServiceInterface = enrollmentServiceInterface;
        this.gradeService = gradeService;
    }


    @PostMapping
    public Enrollment enrollStudentInACourse(@RequestBody EnrollmentDto enrollmentDto){
        return enrollmentServiceInterface.enrollStudentInCourse(enrollmentDto.getStudentId(),
                enrollmentDto.getCourseId());
    }

    @GetMapping("/course/{courseId}")
    public List<EnrollmentDto> getAllStudentInACourse(@PathVariable Long courseId ) {
        return enrollmentServiceInterface.getStudentsByACourse(courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<EnrollmentDto> getAllCourseOfAStudent(@PathVariable Long studentId){
        return enrollmentServiceInterface.getCourseByStudent(studentId);
    }

    @PostMapping("save")
    public List<Enrollment> saveAllEnrollments(@RequestBody List<EnrollmentDto> enrollmentDto){

        return enrollmentServiceInterface.saveAllEnrollments(enrollmentDto);
    }

    @GetMapping("/all-scores")
    public List<ScoreDto> getAllStudentScoresInACourse() {
        return gradeService.getAllStudentScoreInACourse();
    }
}
