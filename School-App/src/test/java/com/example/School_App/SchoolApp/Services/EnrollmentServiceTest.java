//package com.example.School_App.SchoolApp.Services;
//
//import com.example.School_App.SchoolApp.Model.Course;
//import com.example.School_App.SchoolApp.Model.Enrollment;
//import com.example.School_App.SchoolApp.Model.Student;
//import com.example.School_App.SchoolApp.Repository.CourseRepository;
//import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
//import com.example.School_App.SchoolApp.Repository.StudentRepository;
//import com.example.School_App.SchoolApp.SchoolAppDto.CourseDto;
//import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
//import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class EnrollmentServiceTest {
//
//    @Autowired
//    private EnrollmentService enrollmentService;
//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private CourseService courseService;
//
//
//    @Test
//    void testEnrollNewStudent(){
//        Long studentId = 1L;
//        Long courseId = 2L;
//
//        EnrollmentDto enrollmentDto = new EnrollmentDto(1L, 2L);
//
//        Student student = new Student(studentId,"bala","bala@gmail.com","ss2");
//        Course course = new Course(courseId,"physics","phy111");
//
//        Enrollment enrollment = new Enrollment();
//        enrollment.setId(1L);
//        enrollment.setStudent(student);
//        enrollment.setCourse(course);
//
//
//
//        Enrollment enroll = enrollmentService.enrollStudentInCourse(enrollmentDto.getStudentId(), enrollmentDto.getCourseId());
//
//        assertNotNull(enroll);
//        assertEquals("bala", enroll.getStudent().getName());
//        assertEquals("physics", enroll.getCourse().getCourseName());
//
//
//
//
//    }
//
//    @Test
//    void testToGetStudentsInACourse(){
//        Long courseId = 2L;
//
//        Student student = new Student(1L,"abu","abu@gmail.com","ss1");
//        Course course = new Course(courseId,"biology","bio111");
//        Enrollment enrollment = new Enrollment();
//        enrollment.setId(1L);
//        enrollment.setStudent(student);
//        enrollment.setCourse(course);
//
//        Student student1 = new Student(2L,"lami","lami@gmail.com","ss1");
//        Enrollment enrollment1c = new Enrollment();
//        enrollment1c.setId(2L);
//        enrollment1c.setStudent(student1);
//        enrollment1c.setCourse(course);
//
//        List<Enrollment> enrollments = Arrays.asList(enrollment, enrollment1c);
//
//        List<EnrollmentDto>  result = enrollmentService.getStudentsByACourse(courseId);
//        assertNotNull(result);
//        assertEquals(2L, result.get(1).getStudentId());
//        assertEquals(2, result.size());
//        assertEquals(2L, result.get(1).getCourseId());
//        assertEquals(1L, result.get(0).getStudentId());
//
//
//
//    }
//
//    @Test
//    void testToGetAllCoursesAStudentEnrolled(){
//            Long studentId = 1L;
//        StudentDto student = new StudentDto("abu","abu@gmail.com","ss1");
//        studentService.addNewStudent(student);
//
//        Course course = new Course("biology","bio111");
//        Course course1 = new Course("physics","phy111");
//
//        Enrollment enrollment = new Enrollment();
//        enrollment.setStudent(student);
//        enrollment.setCourse(course);
//
//        EnrollmentDto enrollment1c = new EnrollmentDto();
//        enrollment1c.setStudentId(student.getId());
//        enrollment1c.setStudent(student);
//        enrollment1c.setCourse(course1);
//        enrollmentService.saveAllEnrollment(List.of(enrollment, enrollment1c));
//
//     List<EnrollmentDto> result = enrollmentService.getCourseByStudent(studentId);
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals(1L, result.get(0).getCourseId());
//        assertEquals(1L, result.get(0).getStudentId());
//        assertEquals(2L, result.get(1).getStudentId());
//        assertEquals(2L, result.get(1).getCourseId());
//
//    }
//
//}