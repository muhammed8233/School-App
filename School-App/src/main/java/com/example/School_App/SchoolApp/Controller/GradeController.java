package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeDto;
import com.example.School_App.SchoolApp.SchoolAppDto.ScoreDto;
import com.example.School_App.SchoolApp.Services.GradeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "grade")
public class GradeController {
    @Autowired
    private final GradeServiceInterface gradeServiceInterface;

    public GradeController(GradeServiceInterface gradeServiceInterface){
        this.gradeServiceInterface = gradeServiceInterface;
    }

    @PostMapping("score")
    public Grade recordStudentScore(@RequestBody GradeDto gradeDto){
       return gradeServiceInterface.recordStudentScore(gradeDto.getStudentId(),
                gradeDto.getCourseId(), gradeDto.getAssessmentType(), gradeDto.getScore());
    }
    @GetMapping
    public ScoreDto getAllStudentScoreInACourse(@RequestParam Long studentId,
                                                @RequestParam Long courseId){
        return gradeServiceInterface.getAllStudentScoreInACourse(studentId, courseId);
    }

    @GetMapping("/{enrollmentId}")
    public double getFinalScore(@PathVariable Long enrollmentId){
        return gradeServiceInterface.computeFinalScore(enrollmentId);
    }
}
