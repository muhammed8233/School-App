package com.example.School_App.SchoolApp.SchoolAppDto;

import com.example.School_App.SchoolApp.Enum.Status;

import java.time.LocalDate;

public class AttendanceRecordDto {
    private Long courseId;
    private Long studentId;
    private Long present;
    private Long absent;
    private Status status;
    private LocalDate date;

    public AttendanceRecordDto(Long studentId, Long courseId, Long present, Long absent) {
        this.studentId =studentId;
        this.present = present;
        this.absent = absent;
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAbsent() {
        return absent;
    }

    public void setAbsent(Long absent) {
        this.absent = absent;
    }

    public Long getPresent() {
        return present;
    }

    public void setPresent(Long present) {
        this.present = present;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
