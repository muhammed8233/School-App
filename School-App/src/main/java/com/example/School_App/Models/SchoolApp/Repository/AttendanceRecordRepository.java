package com.example.School_App.Models.SchoolApp.Repository;

import com.example.School_App.Models.SchoolApp.AttendanceRecord;
import com.example.School_App.Models.SchoolApp.Course;
import com.example.School_App.Models.SchoolApp.Enrollment;
import com.example.School_App.Models.SchoolApp.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
     List<AttendanceRecord> findStudentByEnrolmentId(Long enrollmentId);

    boolean existsByStudentIdAndCourseIdAndDate(Student student, Course course, LocalDate date);

    boolean existsByEnrollmentAndDate(Enrollment enrollment, LocalDate today);
}

