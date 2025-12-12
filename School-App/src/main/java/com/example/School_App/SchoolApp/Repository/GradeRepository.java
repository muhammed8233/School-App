package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    boolean existsByEnrollment(Enrollment enrollment);

    Optional<Enrollment> existsByEnrollmentAndAssessmentType(Enrollment enrollment, com.example.School_App.SchoolApp.Enum.Assessment assessment);

    Optional<Grade> findById(Long studentId, Long courseId, com.example.School_App.SchoolApp.Enum.Assessment assessment);

    List<Grade> findByEnrollmentId(Long enrollmentId);
}

