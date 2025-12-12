package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
   private  StudentService studentService;


    @Test
    void testToVerifyAddNewStudent(){

        StudentDto studentDto = new StudentDto("bala","bala@gmail.com","ss1");
        Student student = new Student(1L,"bala","bala@gmail.com","ss1");
       when(studentRepository.save(any(Student.class))).thenReturn(student);

       Student result = studentService.addNewStudent(studentDto);

       assertNotNull(result);
       assertEquals(1L, result.getId());
       assertEquals("bala", result.getName());

        verify(studentRepository, times(1)).save(any(Student.class));

    }

    @Test
    void testToViewAllStudent(){
        Student student1= new Student(1L,"musa","musa@gmail.com","ss1");
        Student student = new Student(2L,"bala","bala@gmail.com","ss1");
        List<Student> students = Arrays.asList(student, student1);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getStudents();

        assertNotNull(result);
        assertEquals(2,result.size());
        assertEquals("musa", result.get(1).getName());
        assertEquals("bala", result.get(0).getName());

        verify(studentRepository, times(1)).findAll();

    }



}