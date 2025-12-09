package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Repository.StudentRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoInitializationException;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
   private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;


}