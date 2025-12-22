package com.example.School_App.schoolApp.services;

import com.example.School_App.schoolApp.Enum.Status;
import com.example.School_App.schoolApp.dto.AttendanceRecordDto;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordServiceInterface {
    AttendanceRecordDto getStudentAttendance(Long studentId, Long courseId);
    AttendanceRecordDto markAttendance(Long studentId, Long courseId, LocalDate date, Status status);
    List<AttendanceRecordDto> saveAllAttendanceRecords(List<AttendanceRecordDto> attendanceRecordDtoList);
}
