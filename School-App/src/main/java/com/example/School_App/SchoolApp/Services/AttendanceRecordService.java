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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


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
        Long present = attendanceRecordRepository.countByStudentIdAndCourseIdAndStatus
                (studentId, courseId,Status.PRESENT);
        Long absent = attendanceRecordRepository.countByStudentIdAndCourseIdAndStatus
                (studentId, courseId, Status.ABSENT);

        return new AttendanceRecordDto(studentId, courseId, present, absent);

    }

    @Override
    public void markAttendance(Long studentId, Long courseId, LocalDate date, Status status) {
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
        attendance.setEnrollmentId(enrollment);
        attendance.setDate(date);
        attendance.setStatus(status);

        attendanceRecordRepository.save(attendance);

    }
}
