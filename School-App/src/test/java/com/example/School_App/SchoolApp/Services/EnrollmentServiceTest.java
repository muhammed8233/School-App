package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseDto;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeDto;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EnrollmentServiceTest {
    @Autowired private GradeService gradeService;
    @Autowired private StudentService studentService; // Use the service
    @Autowired private EnrollmentService enrollmentService;
    @Autowired private  CourseService courseService;// Use the service

    @Test
    void canSaveAllGradesUsingServiceOnly() {

        List<Student> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101"))); // Assumed method

            // Ensure the student is enrolled first
        enrollmentService.enrollStudentInCourse(savedStudent.get(0).getId(), savedCourse.get(0).getId());

            // Prepare DTOs for the grades we want to save
        GradeDto request1 = new GradeDto(savedStudent.get(0).getId(), savedCourse.get(0).getId(), Assessment.TEST, 50);
        GradeDto request2 = new GradeDto(savedStudent.get(0).getId(), savedCourse.get(0).getId(), Assessment.EXAM, 90);
        List<GradeDto> requests = List.of(request1, request2);



        List<Grade> savedGrades = gradeService.saveAllGradesFromDto(requests);


            // --- THEN: Assertions (Verify the results) ---
        assertNotNull(savedGrades);
        assertEquals(2, savedGrades.size());
        assertEquals(50, savedGrades.get(0).getScore());
        assertEquals(90, savedGrades.get(0).getScore());
        assertEquals(savedStudent.get(0).getId(), savedGrades.get(0).getEnrollment().getStudent().getId());
    }

    @Test
    void willThrowExceptionIfStudentAlreadyHasGradeForType() {
       List <Student> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));
        enrollmentService.enrollStudentInCourse(savedStudent.get(0).getId(), savedCourse.get(0).getId());

        Grade grades = gradeService.recordStudentScore(savedStudent.get(0).getId(), savedCourse.get(0).getId(), Assessment.TEST, 50);

      assertNotNull(grades);
    }

}
