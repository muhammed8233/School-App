package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.Repository.GradeRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeRequest;

import java.util.List;

public interface GradeServiceInterface  {
    void recordStudentScore(Long studentId, Long courseId, Assessment type);
    List<GradeRequest> getAllStudentScoreInACourse(Long studentId);

    GradeRequest getAllStudentScoreInACourse(Long studentId, Long courseId);
}
