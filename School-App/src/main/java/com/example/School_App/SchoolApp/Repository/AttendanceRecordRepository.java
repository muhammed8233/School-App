package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
}
