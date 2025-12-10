package com.example.School_App.Models.SchoolApp.Services;

import com.example.School_App.Models.SchoolApp.Repository.StudentRepository;
import com.example.School_App.Models.SchoolApp.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testToCheckFindStudentByIdReturnStudentWithTheId(){
        Student student = new Student(1L,"musa","musa@gmail.com","maths");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));


    }


}