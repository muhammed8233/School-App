package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeDto;
import com.example.School_App.SchoolApp.SchoolAppDto.ScoreDto;
import com.example.School_App.SchoolApp.Services.GradeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/grade")
public class GradeController {

    @Autowired private final GradeServiceInterface gradeServiceInterface;

    public GradeController(GradeServiceInterface gradeServiceInterface){
        this.gradeServiceInterface = gradeServiceInterface;
    }

    @PostMapping("score")
    public Grade recordStudentScore(@RequestBody GradeDto gradeDto){
        return gradeServiceInterface.recordStudentScore(gradeDto.getStudentId(),
                gradeDto.getCourseId(), gradeDto.getAssessmentType(), gradeDto.getScore());
    }


    @GetMapping("all-scores")
    public List<ScoreDto> getAllStudentScoreInACourse(){
        return gradeServiceInterface.getAllStudentScoreInACourse();
    }


    @GetMapping("/{enrollmentId}/final-score")
    public double getFinalScore(@PathVariable Long enrollmentId){
        return gradeServiceInterface.computeFinalScore(enrollmentId);
    }


    @PostMapping("save")
    public List<GradeDto> saveAllGrade(@RequestBody List<GradeDto> gradeDtoList){
        return gradeServiceInterface.saveAllGradesFromDto(gradeDtoList);
    }
}
