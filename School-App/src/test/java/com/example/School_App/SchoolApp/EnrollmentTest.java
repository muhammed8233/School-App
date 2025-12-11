package com.example.School_App.SchoolApp;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentTest {
    Enrollment enrollment = new Enrollment();

    @Test
    void testToVerifyEnrollmentDetails(){
        Student student = new Student(1L,"favour","favour@yahoo.com","physics class");
         Course course = new Course(1L,"physics","phy101");
         enrollment.setId(1L);
         enrollment.setStudent(student);
         enrollment.setCourse(course);
        assertEquals(1L, enrollment.getId());
        assertEquals(student, enrollment.getStudent());
        assertEquals(course, enrollment.getCourse());
    }

}