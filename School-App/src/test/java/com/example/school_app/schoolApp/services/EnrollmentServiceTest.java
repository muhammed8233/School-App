package com.example.school_app.schoolApp.services;

import com.example.school_app.schoolApp.repository.EnrollmentRepository;
import com.example.school_app.schoolApp.dto.CourseDto;
import com.example.school_app.schoolApp.dto.EnrollmentDto;
import com.example.school_app.schoolApp.dto.StudentDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
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
        StudentDto savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1"))).get(0);
        CourseDto savedCourse = courseService.saveAllCourses(List.of(new CourseDto("physics", "phy101"))).get(0);

        enrollmentService.enrollStudentInCourse(savedStudent.getStudentId(), savedCourse.getCourseId());

        List<EnrollmentDto> enrollments = enrollmentService.getAllEnrollment();

        assertNotNull(enrollments);
        assertEquals(1, enrollments.size());
        assertEquals(savedStudent.getStudentId(), enrollments.get(0).getStudentId());
        assertEquals(savedCourse.getCourseId(), enrollments.get(0).getCourseId());
    }

//    @Test
//    void testToGetAllCourseForAStudent(){
//        List <StudentDto> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
//        List<CourseDto> savedCourse = courseService.saveAllCourses(List.of(new CourseDto("physics", "phy101")));
//
//        List <StudentDto> students = studentService.saveAllStudents(List.of(new StudentDto("yahaya", "yahaya@gmail.com", "ss1")));
//        List<CourseDto> courses = courseService.saveAllCourses(List.of(new CourseDto("physics", "phy101")));
//
//        EnrollmentDto enrollmentDto = new EnrollmentDto();
//        enrollmentDto.setCourseId(savedCourse.getFirst().getCourseId());
//        enrollmentDto.setStudentId(savedStudent.getFirst().getStudentId());
//
//        EnrollmentDto enrollmentDto1 = new EnrollmentDto();
//        enrollmentDto1.setCourseId(courses.getLast().getCourseId());
//        enrollmentDto1.setStudentId(students.getLast().getStudentId());
//
//        List<EnrollmentDto> enrollmentDtoList = enrollmentService.saveAllEnrollments(List.of(enrollmentDto, enrollmentDto1));
//        List<EnrollmentDto> result = enrollmentService.getCourseByStudent(enrollmentDtoList.getFirst().getStudentId());
//        assertNotNull(result);
//        assertEquals(2, enrollmentDtoList.size());
//        assertEquals(enrollmentDtoList.getFirst().getCourseId(), result.getFirst().getCourseId());
//    }
@Test
void testToGetAllCourseForAStudent() {
    List<StudentDto> savedStudents = studentService.saveAllStudents(List.of(
            new StudentDto("musa", "musa@gmail.com", "ss1"),
            new StudentDto("yahaya", "yahaya@gmail.com", "ss1")
    ));
    List<CourseDto> savedCourses = courseService.saveAllCourses(List.of(
            new CourseDto("physics", "phy101"),
            new CourseDto("chemistry", "che101")
    ));


    Long student1Id = savedStudents.get(0).getStudentId();
    Long course1Id = savedCourses.get(0).getCourseId();
    Long course2Id = savedCourses.get(1).getCourseId();


    EnrollmentDto e1 = new EnrollmentDto();
    e1.setCourseId(course1Id);
    e1.setStudentId(student1Id);

    EnrollmentDto e2 = new EnrollmentDto();
    e2.setCourseId(course2Id);
    e2.setStudentId(student1Id);

    enrollmentService.saveAllEnrollments(List.of(e1, e2));


    List<EnrollmentDto> result = enrollmentService.getCourseByStudent(student1Id);


    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(course1Id, result.get(0).getCourseId());
    assertEquals(course2Id, result.get(1).getCourseId());

}



    @Test
    void testToGetAllEnrollment(){
        List <StudentDto> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<CourseDto> savedCourse = courseService.saveAllCourses(List.of(new CourseDto("physics", "phy101")));

        StudentDto students = studentService.saveAllStudents(List.of(new StudentDto("yahaya", "yahaya@gmail.com", "ss1"))).get(0);
        CourseDto courses = courseService.saveAllCourses(List.of(new CourseDto("physics", "phy101"))).get(0);

        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setCourseId(savedCourse.getFirst().getCourseId());
        enrollmentDto.setStudentId(savedStudent.getFirst().getStudentId());

        EnrollmentDto enrollmentDto1 = new EnrollmentDto();
        enrollmentDto1.setStudentId(students.getStudentId());
        enrollmentDto1.setCourseId(courses.getCourseId());

        enrollmentService.saveAllEnrollments(List.of(enrollmentDto, enrollmentDto1));
        List<EnrollmentDto> result = enrollmentService.getAllEnrollment();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    void testToGetAllStudentByACourse(){
        StudentDto studentDto = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1"))).get(0);
        CourseDto courseDto = courseService.saveAllCourses(List.of(new CourseDto("physics", "phy101"))).get(0);

        StudentDto studentDto2 = studentService.saveAllStudents(List.of(new StudentDto("yahaya", "yahaya@gmail.com", "ss1"))).get(0);

        EnrollmentDto enrollment = new EnrollmentDto();
        enrollment.setCourseId(courseDto.getCourseId());
        enrollment.setStudentId(studentDto.getStudentId());

        EnrollmentDto enrollment1 = new EnrollmentDto();
        enrollment1.setStudentId(studentDto2.getStudentId());
        enrollment1.setCourseId(courseDto.getCourseId());

        enrollmentService.saveAllEnrollments(List.of(enrollment, enrollment1));

        List<EnrollmentDto> result = enrollmentService.getStudentsByACourse(courseDto.getCourseId());

        assertNotNull(result);
        System.out.println(result);
        assertEquals(2, result.size());
        assertEquals(studentDto.getStudentId(), result.get(0).getStudentId());
    }
}
