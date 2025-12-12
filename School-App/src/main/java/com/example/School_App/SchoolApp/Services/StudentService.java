package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements StudentServiceInterface {
    @Autowired
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }



    @Override
    public Student addNewStudent(StudentDto studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setClassName(studentDTO.getClassName());
        return studentRepository.save(student);

    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = studentRepository.findAll();

        return students;
    }



}




