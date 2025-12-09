package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Course;
import com.example.School_App.SchoolApp.Enrollment;
import com.example.School_App.SchoolApp.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndByCourse(Student student, Course course);
}
