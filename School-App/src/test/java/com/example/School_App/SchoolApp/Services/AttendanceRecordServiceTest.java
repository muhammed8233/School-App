package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Repository.AttendanceRecordRepository;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AttendanceRecordServiceTest {

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private AttendanceRecordService attendanceRecordService;

    @BeforeEach
    void setup(){
        attendanceRecordRepository.deleteAll();
    }

    @Test
    void testMarkStudentAttendance(){

    }



}