package com.example.School_App.schoolApp.services;

import com.example.School_App.schoolApp.repository.StudentRepository;
import com.example.School_App.schoolApp.dto.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

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