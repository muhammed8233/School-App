package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Enum.Status;
import com.example.School_App.SchoolApp.SchoolAppDto.AttendanceRecordDto;
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

    @GetMapping(path = "/{studentId}/{courseId}")
    public AttendanceRecordDto getStudentAttendance(
            @RequestParam Long studentId,
            @RequestParam Long courseId){
        return attendanceRecordServiceInterface.
                getStudentAttendance(studentId, courseId);
    }

    @PostMapping
    public void markAttendance(@RequestBody AttendanceRecordDto recordDto) {
         attendanceRecordServiceInterface.markAttendance(recordDto.getStudentId(),
                 recordDto.getCourseId(), recordDto.getDate(), recordDto.getStatus());

    }

}
