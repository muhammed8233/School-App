package com.example.School_App.schoolApp.services;

import com.example.School_App.schoolApp.model.Course;
import com.example.School_App.schoolApp.dto.CourseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CourseServiceInterface {
    List<CourseDto> getStudentCourse();

    CourseDto addNewCourse(CourseDto courseDto);

    @Transactional
    List<CourseDto> saveAllCourses(List<CourseDto> courseDto);

    Course getCourseById(Long courseId);

    List<CourseDto> getAllCoursesAsDto();
}

