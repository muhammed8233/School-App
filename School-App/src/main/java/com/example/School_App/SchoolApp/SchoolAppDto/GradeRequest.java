package com.example.School_App.SchoolApp.SchoolAppDto;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Grade;

public class GradeRequest {
   private Long studentId;
   private Long courseId;
   private double testScore;
   private double examScore;
   private double assignmentScore;
   private double score;
   private Assessment assessmentType;

    public GradeRequest(Long studentId, Long courseId, double examScore, double test, double assignmentScore) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.examScore = examScore;
        this.testScore = test;
        this.assignmentScore = assignmentScore;
    }

    public GradeRequest() {

    }

    public Assessment getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(Assessment assessmentType) {
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

    public double getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(double assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public double getExamScore() {
        return examScore;
    }

    public void setExamScore(double examScore) {
        this.examScore = examScore;
    }

    public double getTestScore() {
        return testScore;
    }

    public void setTestScore(double testScore) {
        this.testScore = testScore;
    }
}
