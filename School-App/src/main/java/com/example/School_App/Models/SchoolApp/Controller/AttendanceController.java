package com.example.School_App.Models.SchoolApp.Controller;

import Dto.AttendanceRecordDto;
import com.example.School_App.Models.SchoolApp.AttendanceRecord;
import com.example.School_App.Models.SchoolApp.Repository.AttendanceRecordRepository;
import com.example.School_App.Models.SchoolApp.Services.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "attendance")
public class AttendanceController {
    private final AttendanceRecordService attendanceRecordService;

    @Autowired
    public AttendanceController(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }

    @PostMapping
    public String markAttendance(@RequestBody AttendanceRecordDto request) {
        return attendanceRecordService.markAttendance(request.getStudentId(),
                request.getCourseId(), request.getDate(), request.getStatus());

    }
}
