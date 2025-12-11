package com.example.School_App.SchoolApp.SchoolAppDto;

import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Enum.Assessment;

public class GradeRequest {
   private Long studentId;
   private Long courseId;
   private String assessmentType;
   private double score;


    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
