package com.example.School_App.schoolApp.services;

import com.example.School_App.schoolApp.model.Course;
import com.example.School_App.schoolApp.model.Enrollment;
import com.example.School_App.schoolApp.model.Student;
import com.example.School_App.schoolApp.dto.EnrollmentDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface EnrollmentServiceInterface {

    List<EnrollmentDto> getAllEnrollment();

    EnrollmentDto enrollStudentInCourse(Long studentId, Long courseId);

    List<EnrollmentDto> getStudentsByACourse(Long courseId);

    @Transactional
    List<EnrollmentDto> saveAllEnrollments(List<EnrollmentDto> enrollmentDto);


    List<EnrollmentDto> getCourseByStudent(Long studentId);

    Enrollment findEnrollmentByStudentAndCourse(Student student, Course course);
}
