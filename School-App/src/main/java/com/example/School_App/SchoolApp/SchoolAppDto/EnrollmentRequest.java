package com.example.School_App.SchoolApp.SchoolAppDto;


public class EnrollmentRequest {
    private Long studentId;
    private Long courseId;

    public EnrollmentRequest(){}
    public EnrollmentRequest(Long studentId, Long courseId) {
        this.studentId = studentId;
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

}
