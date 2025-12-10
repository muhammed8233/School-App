package com.example.School_App.Models.SchoolApp.Repository;

import com.example.School_App.Models.SchoolApp.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseById(Long courseId);
}
