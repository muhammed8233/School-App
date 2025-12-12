package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;

import java.util.List;

public interface EnrollmentServiceInterface {

    Enrollment enrollStudentInCourse(EnrollmentRequest enrollmentRequest);

    List<Enrollment> getStudentsByACourse(Long courseId);

    List<Enrollment> getCourseByStudent(Long studentId);
}
