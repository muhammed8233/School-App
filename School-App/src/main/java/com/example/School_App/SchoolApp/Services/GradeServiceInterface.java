package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeDto;
import com.example.School_App.SchoolApp.SchoolAppDto.ScoreDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface GradeServiceInterface  {
    Grade recordStudentScore(Long studentId, Long courseId, Assessment type, double score);

    ScoreDto getAllStudentScoreInACourse(Long studentId, Long courseId);

    double computeFinalScore(Long enrollmentId);

    @Transactional
    List<Grade> saveAllGradesFromDto(List<GradeDto> gradeRequests);
}
