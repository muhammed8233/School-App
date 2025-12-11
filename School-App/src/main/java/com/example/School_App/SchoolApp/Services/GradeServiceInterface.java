package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;

public interface GradeServiceInterface  {
    void recordStudentScore(Long studentId, Long courseId, Assessment type);

}
