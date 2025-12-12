package com.example.School_App.SchoolApp.Model;

import jakarta.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @OneToOne
    private Enrollment enrollmentId;
    private com.example.School_App.SchoolApp.Enum.Assessment assessmentType;
    private double score;

    public Grade(Long id, Enrollment enrollmentId, com.example.School_App.SchoolApp.Enum.Assessment assessmentType, double score) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.assessmentType = assessmentType;
        this.score = score;
    }

    public Grade() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enrollment getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Enrollment enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public com.example.School_App.SchoolApp.Enum.Assessment getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(com.example.School_App.SchoolApp.Enum.Assessment assessmentType) {
        this.assessmentType = assessmentType;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        if(score >= 0) {
            this.score = score;
        }
    }
}
