package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Enum.Status;
import com.example.School_App.SchoolApp.Model.AttendanceRecord;
import com.example.School_App.SchoolApp.SchoolAppDto.AttendanceRecordDto;
import com.example.School_App.SchoolApp.Services.AttendanceRecordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/attendance")
@Validated
public class AttendanceController {
    private final AttendanceRecordServiceInterface attendanceRecordServiceInterface;

    @Autowired
    public AttendanceController(AttendanceRecordServiceInterface attendanceRecordServiceInterface) {
        this.attendanceRecordServiceInterface = attendanceRecordServiceInterface;
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<AttendanceRecordDto> getStudentAttendance(@PathVariable Long studentId,
                                                                   @PathVariable Long courseId){
        AttendanceRecordDto  records = attendanceRecordServiceInterface.getStudentAttendance(studentId, courseId);

        return ResponseEntity.ok(records);
    }

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceRecordDto recordDto) {
         attendanceRecordServiceInterface.markAttendance(recordDto.getStudentId(),
                 recordDto.getCourseId(), recordDto.getDate(), recordDto.getStatus());

         return ResponseEntity.ok("marked successfully");
    }

    @PostMapping("save")
    public ResponseEntity<List<AttendanceRecordDto>> saveAllAttendanceRecord(@RequestBody List<AttendanceRecordDto>
                                                                      attendanceRecordDtoList){
        List<AttendanceRecordDto> recordDtosv = attendanceRecordServiceInterface.saveAllAttendanceRecords(attendanceRecordDtoList);

        return new ResponseEntity<>(recordDtosv, HttpStatus.CREATED);
    }

}
