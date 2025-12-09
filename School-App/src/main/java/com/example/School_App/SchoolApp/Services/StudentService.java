package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void registerNewStudent(Student student){
        Optional<Student> optional = studentRepository.
                findStudentByEmail(student.getEmail());
        if(optional.isPresent()){
            throw new IllegalStateException("Student with email "+student.getEmail() +" already exist");
        }
        studentRepository.save(student);
    }

//    public void DeleteStudent(Long studentId){
//        boolean exist = studentRepository.existsById(studentId);
//        if (!exist){
//            throw new IllegalStateException("student with id "+ studentId + " does not exist");
//        }
//        studentRepository.deleteById(studentId);
//    }
//
//    @Transactional
//    public void updateStudent(Long studentId, String name, String email){
//        Student student = studentRepository.findById(studentId).orElseThrow(() ->
//                new IllegalStateException("student with id "+ studentId + " does not exist"));
//
//        if(name != null && !Objects.equals(student.getName(), name)){
//            student.setName(name);
//        }
//        if(email != null && !Objects.equals(student.getEmail(), email)){
//            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
//            if(optionalStudent.isPresent()){
//                throw new IllegalStateException("Email is already taken");
//            }
//            student.setEmail(email);
//        }
//    }
}
