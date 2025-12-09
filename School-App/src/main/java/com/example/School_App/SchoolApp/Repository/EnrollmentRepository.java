package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
