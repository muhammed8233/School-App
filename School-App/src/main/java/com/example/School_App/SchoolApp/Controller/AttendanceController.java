package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Enum.Status;
import com.example.School_App.SchoolApp.Model.AttendanceRecord;
import com.example.School_App.SchoolApp.SchoolAppDto.AttendanceRecordDto;
import com.example.School_App.SchoolApp.Services.AttendanceRecordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/attendance")
public class AttendanceController {
    private final AttendanceRecordServiceInterface attendanceRecordServiceInterface;

    @Autowired
    public AttendanceController(AttendanceRecordServiceInterface attendanceRecordServiceInterface) {
        this.attendanceRecordServiceInterface = attendanceRecordServiceInterface;
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public AttendanceRecordDto getStudentAttendance(@PathVariable Long studentId,
                                                    @PathVariable Long courseId){
        return attendanceRecordServiceInterface.
                getStudentAttendance(studentId, courseId);
    }

    @PostMapping("/mark")
    public void markAttendance(@RequestBody AttendanceRecordDto recordDto) {
         attendanceRecordServiceInterface.markAttendance(recordDto.getStudentId(),
                 recordDto.getCourseId(), recordDto.getDate(), recordDto.getStatus());

    }
    @PostMapping("save")
    public List<AttendanceRecord> saveAllAttendanceRecord(@RequestBody List<AttendanceRecordDto>
                                                                      attendanceRecordDtoList){
        return attendanceRecordServiceInterface.saveAllAttendanceRecords(attendanceRecordDtoList);
    }

}
