package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseCode(String courseCode);
}
