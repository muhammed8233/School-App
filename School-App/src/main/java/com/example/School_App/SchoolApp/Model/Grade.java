package com.example.School_App.SchoolApp.Model;

import com.example.School_App.SchoolApp.Enum.Assessment;
import jakarta.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @OneToOne
    private Enrollment enrollment;
    private Assessment assessmentType;
    private double score;

    public Grade(Enrollment enrollment, Assessment assessmentType, double score) {
        this.enrollment = enrollment;
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

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
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

    @Override
    public String toString() {
        return "Grade{" +
                "assessmentType=" + assessmentType +
                ", id=" + id +
                ", enrollmentId=" + enrollment +
                ", score=" + score +
                '}';
    }
}
