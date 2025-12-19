package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseDto;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnrollmentServiceTest {
    @Autowired private GradeService gradeService;
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
        List <StudentDto> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<CourseDto> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

           enrollmentService.enrollStudentInCourse(savedStudent.get(0).getStudentId(), savedCourse.get(0).getCourseId());
          List<EnrollmentDto> enrollments = enrollmentService.getAllEnrollment();
           assertNotNull(enrollments);
           assertEquals(1, enrollments.size());
           assertEquals(1, enrollments.get(0).getStudentId());
           assertEquals(1, enrollments.get(0).getCourseId());
           assertEquals(1, enrollments.get(0).getEnrollmentId());

    }

    @Test
    void testToGetAllCourseForAStudent(){
        List <StudentDto> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<CourseDto> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        List <StudentDto> students = studentService.saveAllStudents(List.of(new StudentDto("yahaya", "yahaya@gmail.com", "ss1")));
        List<CourseDto> courses = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setCourseId(savedCourse.getFirst().getCourseId());
        enrollmentDto.setStudentId(savedStudent.getFirst().getStudentId());

        EnrollmentDto enrollmentDto1 = new EnrollmentDto();
        enrollmentDto1.setCourseId(courses.getLast().getCourseId());
        enrollmentDto1.setStudentId(students.getLast().getStudentId());

        List<EnrollmentDto> enrollmentDtoList = enrollmentService.saveAllEnrollments(List.of(enrollmentDto, enrollmentDto1));
        List<EnrollmentDto> result = enrollmentService.getCourseByStudent(enrollmentDtoList.get(0).getStudentId());
        assertNotNull(result);
        assertEquals(2, enrollmentDtoList.size());
        assertEquals(1, result.getFirst().getCourseId());
    }

    @Test
    void testToGetAllStudentByACourse(){
        List <StudentDto> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<CourseDto> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        List <StudentDto> students = studentService.saveAllStudents(List.of(new StudentDto("yahaya", "yahaya@gmail.com", "ss1")));
        List<CourseDto> courses = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setCourseId(savedCourse.getFirst().getCourseId());
        enrollmentDto.setStudentId(savedStudent.getFirst().getStudentId());

        EnrollmentDto enrollmentDto1 = new EnrollmentDto();
        enrollmentDto1.setStudentId(students.getLast().getStudentId());
        enrollmentDto1.setCourseId(courses.getLast().getCourseId());

        List<EnrollmentDto> enrollmentDtoList = enrollmentService.saveAllEnrollments(List.of(enrollmentDto, enrollmentDto1));
        List<EnrollmentDto> result = enrollmentService.getStudentsByACourse(enrollmentDtoList.get(0).getCourseId());
        assertNotNull(result);
        assertEquals(2, enrollmentDtoList.size());
        assertEquals(1, result.getFirst().getStudentId());

    }

    @Test
    void testToGetAllEnrollment(){
        List <StudentDto> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<CourseDto> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        List <StudentDto> students = studentService.saveAllStudents(List.of(new StudentDto("yahaya", "yahaya@gmail.com", "ss1")));
        List<CourseDto> courses = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setCourseId(savedCourse.getFirst().getCourseId());
        enrollmentDto.setStudentId(savedStudent.getFirst().getStudentId());

        EnrollmentDto enrollmentDto1 = new EnrollmentDto();
        enrollmentDto1.setStudentId(students.getLast().getStudentId());
        enrollmentDto1.setCourseId(courses.getLast().getCourseId());

        enrollmentService.saveAllEnrollments(List.of(enrollmentDto, enrollmentDto1));
        List<EnrollmentDto> result = enrollmentService.getAllEnrollment();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
