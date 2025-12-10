package com.example.School_App.Models.SchoolApp.Repository;

import com.example.School_App.Models.SchoolApp.Course;
import com.example.School_App.Models.SchoolApp.Enrollment;
import com.example.School_App.Models.SchoolApp.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndByCourse(Student student, Course course);
    List<Enrollment> findStudentByCourseId(Long courseId);
    List<Enrollment> findCourseByStudentId(Long studentId);

    boolean findByEnrollmentAndDate(Enrollment enrollment, LocalDate date);
}