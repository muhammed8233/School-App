package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;

import java.util.List;

public interface EnrollmentServiceInterface {

    void enrollStudentInCourse(Long studentId, Long courseId);

    List<StudentDto> getStudentsByACourse(Long courseId);

    List<CourseRequest> getCourseByStudent(Long studentId);
}
