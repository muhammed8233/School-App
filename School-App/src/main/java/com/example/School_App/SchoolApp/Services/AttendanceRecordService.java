package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.AttendanceRecord;
import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Enum.Status;
import com.example.School_App.SchoolApp.Repository.AttendanceRecordRepository;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.AttendanceRecordDto;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class AttendanceRecordService implements AttendanceRecordServiceInterface {

    @Autowired
    private final AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final EnrollmentRepository enrollmentRepository;

    public AttendanceRecordService(AttendanceRecordRepository attendanceRecordRepository, StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public AttendanceRecordDto getStudentAttendance(Long studentId, Long courseId) {
        Long present = attendanceRecordRepository.countByEnrollmentStudentIdAndEnrollmentCourseIdAndStatus(
                studentId, courseId, Status.PRESENT);

        Long absent = attendanceRecordRepository.countByEnrollmentStudentIdAndEnrollmentCourseIdAndStatus(
                studentId, courseId, Status.ABSENT);

        AttendanceRecord lastRecord = attendanceRecordRepository
                .findTopByEnrollmentStudentIdAndEnrollmentCourseIdOrderByDateDesc(studentId, courseId);


        AttendanceRecordDto dto = new AttendanceRecordDto();
        dto.setStudentId(studentId);
        dto.setCourseId(courseId);
        dto.setAbsent(absent);
        dto.setPresent(present);

        if (lastRecord != null) {
            dto.setDate(lastRecord.getDate());
            dto.setStatus(lastRecord.getStatus());
        }

        return dto;
    }


    @Override
    public AttendanceRecord markAttendance(Long studentId, Long courseId, LocalDate date, Status status) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new RuntimeException("Course not found"));

       Enrollment  enrollment = enrollmentRepository.findByStudentAndCourse(student, course);

        if (attendanceRecordRepository.existsByEnrollmentAndDate(enrollment, date)) {
            throw new IllegalStateException("attendance for "+ enrollment.getStudent().getName()+
                    " has been already marked today");
        }
        AttendanceRecord attendance = new AttendanceRecord();
        attendance.setEnrollment(enrollment);
        attendance.setDate(date);
        attendance.setStatus(status);

       return attendanceRecordRepository.save(attendance);

    }

    @Override
    public List<AttendanceRecord> saveAllAttendanceRecords(List<AttendanceRecordDto> attendanceRecordDtoList) {
        if (attendanceRecordDtoList == null || attendanceRecordDtoList.isEmpty()) {
            throw new RuntimeException("Attendance record cannot be empty");
        }
        List<AttendanceRecord> records = new ArrayList<>();

        for (AttendanceRecordDto dto : attendanceRecordDtoList) {
            AttendanceRecord record = new AttendanceRecord();
            Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(dto.getStudentId(), dto.getCourseId());

            record.setEnrollment(enrollment);
            record.setDate(dto.getDate());
            record.setStatus(dto.getStatus());

            records.add(record);
        }
        return attendanceRecordRepository.saveAll(records);
    }


}
