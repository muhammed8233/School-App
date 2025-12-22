package com.example.School_App.schoolApp.repository;

import com.example.School_App.schoolApp.model.Course;
import com.example.School_App.schoolApp.model.Enrollment;
import com.example.School_App.schoolApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);

    Enrollment findByStudentAndCourse(Student student, Course course);
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);

    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);
}