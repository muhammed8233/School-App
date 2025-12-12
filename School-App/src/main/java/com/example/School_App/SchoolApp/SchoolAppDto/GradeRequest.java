package com.example.School_App.SchoolApp.SchoolAppDto;

import com.example.School_App.SchoolApp.Model.Grade;

public class GradeRequest {
   private Long studentId;
   private Long courseId;
   private Grade test;
   private Grade exam;
   private Grade assignment;
   private double score;
   private com.example.School_App.SchoolApp.Enum.Assessment assessmentType;

    public GradeRequest(Long studentId, Long courseId, Grade exam, Grade test, Grade assignment) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.exam = exam;
        this.test = test;
        this.assignment = assignment;
    }

    public GradeRequest() {

    }


    public com.example.School_App.SchoolApp.Enum.Assessment getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(com.example.School_App.SchoolApp.Enum.Assessment assessmentType) {
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
