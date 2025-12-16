package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface EnrollmentServiceInterface {

    Enrollment enrollStudentInCourse(Long studentId, Long courseId);

    List<EnrollmentDto> getStudentsByACourse(Long courseId);

    @Transactional
    List<Enrollment> saveAllEnrollments(List<EnrollmentDto> enrollmentDto);


    List<EnrollmentDto> getCourseByStudent(Long studentId);

    Enrollment findEnrollmentByStudentAndCourse(Student student, Course course);
}
