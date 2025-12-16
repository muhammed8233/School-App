package com.example.School_App.SchoolApp.Repository;

import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {


}
