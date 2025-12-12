package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;

import java.util.List;

public interface CourseServiceInterface {
    List<Course> getStudentCourse();

    Course addNewCourse(CourseRequest courseRequest);
}
