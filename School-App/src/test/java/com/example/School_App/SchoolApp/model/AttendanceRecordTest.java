package com.example.School_App.SchoolApp.model;

import com.example.School_App.SchoolApp.Model.AttendanceRecord;
import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Enum.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceRecordTest {
    AttendanceRecord attendance = new AttendanceRecord();

    @Test
    void testToVerifyStudentAttendanceRecord(){
        Student student = new Student(1L, "faith","faith@gamil.com","math class");
        Course course = new Course(1L, "mathematics","math111");
        Enrollment enrollment = new Enrollment(1L, student,course);
        attendance.setId(2L);
        attendance.setEnrollment(enrollment);
        attendance.setStatus(Status.PRESENT);
        attendance.setDate(LocalDate.of(2025, Month.DECEMBER, 9));
        assertEquals(2L,attendance.getId());
        assertEquals(enrollment, attendance.getEnrollment());
        assertEquals(Status.PRESENT, attendance.getStatus());
        assertEquals(LocalDate.of(2025, Month.DECEMBER, 9), attendance.getDate());

    }


}