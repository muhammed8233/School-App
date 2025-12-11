package com.example.School_App.SchoolApp.SchoolAppDto;

public class ScoreDto {
    private Long studentId;
    private String StudentName;
    private double testScore;
    private double ExamScore;
    private double finalScore;

    public double getExamScore() {
        return ExamScore;
    }

    public void setExamScore(double examScore) {
        ExamScore = examScore;
    }

    public double getTestScore() {
        return testScore;
    }

    public void setTestScore(double testScore) {
        this.testScore = testScore;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }
}
