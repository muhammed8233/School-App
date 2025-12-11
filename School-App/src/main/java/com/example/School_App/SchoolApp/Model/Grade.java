package com.example.School_App.SchoolApp.Model;

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
    private Assignment assignmentType;
    private int score;

    public Grade(Long id, Enrollment enrollmentId, Assignment assignmentType, int score) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.assignmentType = assignmentType;
        this.score = score;
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

    public Assignment getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(Assignment assignmentType) {
        this.assignmentType = assignmentType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
