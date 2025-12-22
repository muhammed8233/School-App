package com.example.School_App.schoolApp.services;

import com.example.School_App.schoolApp.Enum.Assessment;
import com.example.School_App.schoolApp.dto.GradeDto;
import com.example.School_App.schoolApp.dto.ScoreDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface GradeServiceInterface  {
    GradeDto recordStudentScore(Long studentId, Long courseId, Assessment type, double score);

   List<ScoreDto> getAllStudentScoreInACourse();

    double computeFinalScore(Long enrollmentId);

    @Transactional
    List<GradeDto> saveAllGradesFromDto(List<GradeDto> gradeRequests);
}
