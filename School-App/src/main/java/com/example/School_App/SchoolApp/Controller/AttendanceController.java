package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Enum.Status;
import com.example.School_App.SchoolApp.SchoolAppDto.AttendanceRecordDto;
import com.example.School_App.SchoolApp.Services.AttendanceRecordService;
import com.example.School_App.SchoolApp.Services.AttendanceRecordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "attendance")
public class AttendanceController {
    private final AttendanceRecordServiceInterface attendanceRecordServiceInterface;

    @Autowired
    public AttendanceController(AttendanceRecordServiceInterface attendanceRecordServiceInterface) {
        this.attendanceRecordServiceInterface = attendanceRecordServiceInterface;
    }

    @PostMapping
    public void markAttendance(@PathVariable Long studentId,
                               @PathVariable Long courseId,
                               @PathVariable LocalDate date,
                               @PathVariable Status status,
                               @RequestBody AttendanceRecordDto recordDto) {
         attendanceRecordServiceInterface.markAttendance(recordDto.getStudentId(),
                recordDto.getCourseId(), recordDto.getDate(), recordDto.getStatus());

    }
}
