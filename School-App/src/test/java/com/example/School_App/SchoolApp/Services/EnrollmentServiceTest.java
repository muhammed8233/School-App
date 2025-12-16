package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseDto;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnrollmentServiceTest {
    @Autowired private StudentService studentService;
    @Autowired private EnrollmentService enrollmentService;
    @Autowired private  CourseService courseService;
    @Autowired private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setup(){
        enrollmentRepository.deleteAll();
    }

    @Test
    void testToEnrollStudentInACourse() {
        List <Student> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

       try{
           enrollmentService.enrollStudentInCourse(savedStudent.get(0).getId(), savedCourse.get(0).getId());
         enrollmentService.enrollStudentInCourse(savedStudent.getFirst().getId(), savedCourse.getFirst().getId());
          List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
           assertNotNull(enrollments);
           assertEquals(2, enrollments.size());
           assertEquals("musa", enrollments.get(1).getStudent().getName());
           assertEquals("phy101", enrollments.get(0).getCourse().getCourseCode());
           assertEquals(1, enrollments.get(0).getStudent().getId());
       }catch (RuntimeException e){
           System.err.println(e.getMessage());
       }
    }

    @Test
    void testToViewAllStudentEnrolledInACourse(){
        List <Student> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));
        List <Student> students = studentService.saveAllStudents(List.of(new StudentDto("aliyu", "aliyu@gmail.com", "ss1")));
        List<Course> courses = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));


      Enrollment save =  enrollmentService.enrollStudentInCourse(savedStudent.get(0).getId(), savedCourse.get(0).getId());
      Enrollment save2 = enrollmentService.enrollStudentInCourse(students.getFirst().getId(), courses.getFirst().getId());

       List<Enrollment> results = enrollmentService.getAllEnrollments();

       assertNotNull(results);
       assertEquals(2, results.size());
       assertEquals("aliyu", results.get(1).getStudent().getName());
    }


}
