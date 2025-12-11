package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeRequest;
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
    public void recordStudentScore(@RequestBody GradeRequest gradeRequest){
        gradeServiceInterface.recordStudentScore(gradeRequest.getStudentId(),
                gradeRequest.getCourseId(), gradeRequest.getAssessmentType());
    }
}
