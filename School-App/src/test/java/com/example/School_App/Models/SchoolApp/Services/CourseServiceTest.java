package com.example.School_App.Models.SchoolApp.Services;

import com.example.School_App.Models.SchoolApp.Repository.StudentRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
   private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;


}