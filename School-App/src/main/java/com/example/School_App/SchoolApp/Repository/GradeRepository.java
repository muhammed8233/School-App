package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    boolean existsByEnrollmentAndAssessmentType(Enrollment enrollment, Assessment assessmentType);

    List<Grade> findByEnrollmentId(Long enrollmentId);

    @Query("SELECT g.score FROM Grade g WHERE g.enrollment.id = :enrollmentId AND g.assessmentType = :assessment")
    Double findScore(@Param("enrollmentId") Long enrollmentId, @Param("assessment") Assessment assessment);

}


