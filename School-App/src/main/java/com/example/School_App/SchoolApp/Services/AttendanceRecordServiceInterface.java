package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Status;
import com.example.School_App.SchoolApp.Model.AttendanceRecord;
import com.example.School_App.SchoolApp.SchoolAppDto.AttendanceRecordDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AttendanceRecordServiceInterface {
    AttendanceRecordDto getStudentAttendance(Long studentId, Long courseId);
    AttendanceRecordDto markAttendance(Long studentId, Long courseId, LocalDate date, Status status);
    List<AttendanceRecordDto> saveAllAttendanceRecords(List<AttendanceRecordDto> attendanceRecordDtoList);
}
