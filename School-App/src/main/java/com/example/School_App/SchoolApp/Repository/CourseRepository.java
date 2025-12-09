package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    Optional<Course> findCourseById(String courseCode);
}
