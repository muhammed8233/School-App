package com.example.School_App.SchoolApp.Model;

import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import jakarta.persistence.*;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;

    @ManyToOne
    @JoinColumn(
            name = "course_id"
    )
    private Course course;


    public Enrollment(){}

    public Enrollment(Long id, Student student, Course course ) {
        this.id = id;
        this.student = student;
        this.course = course;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseRequest getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
