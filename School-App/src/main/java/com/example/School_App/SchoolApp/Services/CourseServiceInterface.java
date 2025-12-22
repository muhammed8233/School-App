package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CourseServiceInterface {
    List<CourseDto> getStudentCourse();

    void addNewCourse(CourseDto courseDto);

    @Transactional
    List<CourseDto> saveAllCourses(List<CourseDto> courseDto);

    Course getCourseById(Long courseId);

    List<CourseDto> getAllCoursesAsDto();
}

