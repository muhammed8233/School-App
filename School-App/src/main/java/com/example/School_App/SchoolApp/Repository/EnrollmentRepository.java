package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);

    Enrollment findByStudentAndCourse(Student student, Course course);
}