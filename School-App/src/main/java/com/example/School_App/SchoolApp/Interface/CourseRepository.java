package com.example.School_App.SchoolApp.Interface;

import com.example.School_App.SchoolApp.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
