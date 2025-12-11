package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements StudentServiceInt {
    @Autowired
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }



    @Override
    public void addNewStudent(StudentDto studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setClassName(studentDTO.getClassName());
        studentRepository.save(student);

    }

    @Override
    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDto = new ArrayList<>();

        for (Student student : students) {
            StudentDto studentDto1 = new StudentDto();
            studentDto1.setName(student.getName());
            studentDto1.setEmail(student.getEmail());
            studentDto1.setClassName(student.getClassName());
        }
        return studentDto;
    }



}




