package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.ScoreDto;

public interface GradeServiceInterface  {
    void recordStudentScore(Long studentId, Long courseId, Assessment type);

    ScoreDto getAllStudentScoreInACourse(Long studentId, Long courseId);

    double computeFinalScore(Long enrollmentId);
}
