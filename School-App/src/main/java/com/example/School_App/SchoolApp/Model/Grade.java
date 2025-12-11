package com.example.School_App.SchoolApp.Model;

import com.example.School_App.SchoolApp.Enum.Assessment;
import jakarta.persistence.*;
import org.hibernate.sql.ast.tree.update.Assignment;

@Entity
public class Grade {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @OneToOne
    private Enrollment enrollmentId;
    private Assessment assessmentType;
    private int score;

    public Grade(Long id, Enrollment enrollmentId, Assessment assessmentType, int score) {
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

    public Assessment getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(Assessment assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
