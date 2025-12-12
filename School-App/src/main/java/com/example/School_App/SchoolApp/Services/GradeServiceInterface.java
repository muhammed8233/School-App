package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeRequest;

public interface GradeServiceInterface  {
    void recordStudentScore(Long studentId, Long courseId, Assessment type);

    GradeRequest getAllStudentScoreInACourse(Long studentId, Long courseId);

    double computeFinalScore(Long enrollmentId);
}
