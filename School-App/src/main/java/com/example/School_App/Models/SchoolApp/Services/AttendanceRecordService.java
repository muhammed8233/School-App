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
                                   CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
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

//    public String markAttendance(Long studentId, Long courseId,
//                                 LocalDate date, Status attendanceStatus) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student with id " + studentId + " not found"));
//
//        Course course = courseRepository.findCourseById(courseId).orElseThrow(()
//                -> new IllegalStateException("course with id " + courseId + " not found"));
//
//
//
//
//        if (attendanceRecordRepository.existsByStudentIdAndCourseIdAndDate(student, course, date)) {
//            return "Attendance already marked";
//        }
//        return "Attendance mark as" + attendanceStatus;
//    }

    public String markAttendance(Long enrollmentId, Status status) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        LocalDate today = LocalDate.now();

        // Avoid duplicate attendance
        if (attendanceRecordRepository.existsByEnrollmentAndDate(enrollment, today)) {
            return "Attendance already marked for today.";
        }

        AttendanceRecord attendance = new AttendanceRecord();
        attendance.setEnrollmentId(enrollment);
        attendance.setDate(today);
        attendance.setStatus(status);

        attendanceRecordRepository.save(attendance);

        return "Attendance marked as " + status;

    }
}
