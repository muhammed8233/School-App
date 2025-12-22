package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
   private  StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp(){
        studentRepository.deleteAll();
    }

    @Test
    void testToVerifyAddNewStudent(){
        StudentDto studentDto = new StudentDto("bala","bala@gmail.com","ss1");

       StudentDto result = studentService.addNewStudent(studentDto);

       assertNotNull(result);
       assertEquals("bala@gmail.com", result.getEmail());
       assertEquals("bala", result.getName());

    }

    @Test
    void testToViewAllStudent(){
        StudentDto student = new StudentDto("musa","musa@hotmail.com","ss1");
        StudentDto student1 = new StudentDto("bala","bala@gmail.com","ss1");
        studentService.saveAllStudents(List.of(student1, student));

        List<StudentDto> result = studentService.getStudents();

        assertNotNull(result);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        assertEquals(2,result.size());
        assertEquals("musa@hotmail.com", result.get(1).getEmail());
        assertEquals("bala", result.get(0).getName());
        assertEquals("musa", result.get(1).getName());

    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        StudentDto invalidDto = new StudentDto("", "bala@gmail.com", "ss1");

        StudentDto dto = studentService.addNewStudent(invalidDto);
       assertThat(dto.getName()).isBlank();
    }
}