package com.example.School_App.SchoolApp;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class AttendanceRecord {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "enrollment_Id",
            nullable = false
    )
    private Enrollment enrollmentId;
    private LocalDate date;
    private Status status;

    public AttendanceRecord(){}
    public AttendanceRecord(Long id, Enrollment enrollmentId, Status status, LocalDate date) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.status = status;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
