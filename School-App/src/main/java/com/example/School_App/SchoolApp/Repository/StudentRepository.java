package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentById(Long studentId);
}
