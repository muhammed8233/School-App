package com.example.School_App.SchoolApp.Interface;

import com.example.School_App.SchoolApp.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
