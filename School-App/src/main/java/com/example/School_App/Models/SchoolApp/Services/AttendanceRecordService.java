package com.example.School_App.Models.SchoolApp.Services;

import com.example.School_App.Models.SchoolApp.AttendanceRecord;
import com.example.School_App.Models.SchoolApp.Course;
import com.example.School_App.Models.SchoolApp.Enrollment;
import com.example.School_App.Models.SchoolApp.Enum.Status;
import com.example.School_App.Models.SchoolApp.Repository.AttendanceRecordRepository;
import com.example.School_App.Models.SchoolApp.Repository.CourseRepository;
import com.example.School_App.Models.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.Models.SchoolApp.Repository.StudentRepository;
import com.example.School_App.Models.SchoolApp.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttendanceRecordService {
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public AttendanceRecordService(AttendanceRecordRepository attendanceRecordRepository,
                                   StudentRepository studentRepository,
                                   CourseRepository courseRepository,
                                   EnrollmentRepository enrollmentRepository) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Map<Status, Long> viewAttendanceOfStudentByEnrollmentId(Long enrollmentId) {
        List<AttendanceRecord> records = attendanceRecordRepository.
                findStudentByEnrolmentId(enrollmentId);

        return records.stream().collect(Collectors.
                groupingBy(AttendanceRecord::getStatus, Collectors.counting()));

    }

    public String markAttendance(Long studentId, Long courseId, LocalDate date, Status status) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = enrollmentRepository.findByStudentAndCourse(student, course)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        if (attendanceRepository.existsByEnrollmentAndDate(enrollment, date)) {
            return "Attendance already marked for this date.";
        }

        Attendance attendance = new Attendance();
        attendance.setEnrollment(enrollment);
        attendance.setDate(date);
        attendance.setStatus(status);

        attendanceRepository.save(attendance);

        return "Attendance marked as " + status;
    }



}
