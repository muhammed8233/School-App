package com.example.School_App.schoolApp.repository;

import com.example.School_App.schoolApp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseCode(String courseCode);



}
