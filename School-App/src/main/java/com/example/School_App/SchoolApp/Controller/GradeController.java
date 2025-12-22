package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeDto;
import com.example.School_App.SchoolApp.SchoolAppDto.ScoreDto;
import com.example.School_App.SchoolApp.Services.GradeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/grade")
@Validated
public class GradeController {

    @Autowired private final GradeServiceInterface gradeServiceInterface;

    public GradeController(GradeServiceInterface gradeServiceInterface){
        this.gradeServiceInterface = gradeServiceInterface;
    }

    @PostMapping("score")
    public ResponseEntity<String> recordStudentScore(@RequestBody GradeDto gradeDto){
         gradeServiceInterface.recordStudentScore(gradeDto.getStudentId(),
                gradeDto.getCourseId(), gradeDto.getAssessmentType(), gradeDto.getScore());
         return ResponseEntity.ok("score recorded successfully");
    }


    @GetMapping("all-scores")
    public ResponseEntity<List<ScoreDto>> getAllStudentScoreInACourse(){
        List<ScoreDto> records = gradeServiceInterface.getAllStudentScoreInACourse();

        return new ResponseEntity<>(records, HttpStatus.CREATED);
    }


    @GetMapping("/{enrollmentId}/final-score")
    public ResponseEntity<Double> getFinalScore(@PathVariable Long enrollmentId){
        Double  computeFinalScore = gradeServiceInterface.computeFinalScore(enrollmentId);

        return ResponseEntity.ok(computeFinalScore);
    }
}
