package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Enum.Status;
import com.example.School_App.SchoolApp.Model.AttendanceRecord;
import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    boolean existsByEnrollmentAndDate(Enrollment enrollment, LocalDate today);
    Long countByStatus(Status status);
}

