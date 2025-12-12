package com.example.School_App.SchoolApp.SchoolAppDto;

public class ScoreDto {
    private Long studentId;
    private Long courseId;
    private Double exam;
    private Double test;
    private Double assignment;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Double getExam() {
        return exam;
    }

    public void setExam(Double exam) {
        this.exam = exam;
    }

    public Double getAssignment() {
        return assignment;
    }

    public void setAssignment(Double assignment) {
        this.assignment = assignment;
    }

    public Double getTest() {
        return test;
    }

    public void setTest(Double test) {
        this.test = test;
    }
}
