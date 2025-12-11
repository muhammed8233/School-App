package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
