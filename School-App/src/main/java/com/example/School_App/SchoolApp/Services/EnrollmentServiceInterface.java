package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface EnrollmentServiceInterface {

    Enrollment enrollStudentInCourse(Long studentId, Long courseId);

    List<EnrollmentDto> getStudentsByACourse(Long courseId);

    List<EnrollmentDto> getCourseByStudent(Long studentId);
    @Transactional
    List<Enrollment> saveAllEnrollments(List<EnrollmentDto> enrollmentDto);


}
