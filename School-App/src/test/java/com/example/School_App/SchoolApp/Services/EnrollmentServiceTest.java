package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;


    @Test
    void testEnrollNewStudent(){
        Long studentId = 1L;
        Long courseId = 2L;

        EnrollmentRequest enrollmentRequest = new EnrollmentRequest(1L, 2L);

        Student student = new Student(studentId,"bala","bala@gmail.com","ss2");
        Course course = new Course(courseId,"physics","phy111");

        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        Enrollment enroll = enrollmentService.enrollStudentInCourse(enrollmentRequest);

        assertNotNull(enroll);
        assertEquals("bala", enroll.getStudent().getName());
        assertEquals("physics", enroll.getCourse().getCourseName());

        verify(enrollmentRepository, times(1)).save(any(Enrollment.class));
        verify(studentRepository, times(1)).findById(studentId);
        verify(courseRepository, times(1)).findById(courseId);



    }

    @Test
    void testToGetStudentsInACourse(){
        Long courseId = 2L;

        Student student = new Student(1L,"abu","abu@gmail.com","ss1");
        Course course = new Course(courseId,"biology","bio111");
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Student student1 = new Student(2L,"lami","lami@gmail.com","ss1");
        Course course1 = new Course(courseId,"biology","bio111");
        Enrollment enrollment1c = new Enrollment();
        enrollment1c.setId(2L);
        enrollment1c.setStudent(student1);
        enrollment1c.setCourse(course1);

        List<Enrollment> enrollments = Arrays.asList(enrollment, enrollment1c);

        when(enrollmentRepository.findAll()).thenReturn(enrollments);

        List<Enrollment> result = enrollmentRepository.findAll();

        assertNotNull(result);
        assertEquals("lami", result.get(1).getStudent().getName());
        assertEquals(2, result.size());
        assertEquals(2L, result.get(1).getCourse().getCourseId());
        assertEquals(2L, result.get(0).getCourse().getCourseId());


        verify(enrollmentRepository,times(1)).findAll();
    }

    @Test
    void testToGetAllCoursesAStudentEnrolled(){
        Long studentId = 1L;

        Student student = new Student(studentId,"abu","abu@gmail.com","ss1");
        Course course = new Course(1L,"biology","bio111");
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Student student1 = new Student(studentId,"abu","abu@gmail.com","ss1");
        Course course1 = new Course(2L,"physics","phy111");
        Enrollment enrollment1c = new Enrollment();
        enrollment1c.setId(2L);
        enrollment1c.setStudent(student1);
        enrollment1c.setCourse(course1);

        List<Enrollment> enrollments = Arrays.asList(enrollment, enrollment1c);

        when(enrollmentRepository.findAll()).thenReturn(enrollments);

        List<Enrollment> result = enrollmentRepository.findAll();

        assertNotNull(result);
        assertEquals("abu", result.get(0).getStudent().getName());
        assertEquals("biology", result.get(0).getCourse().getCourseName());
        assertEquals("physics", result.get(1).getCourse().getCourseName());
        assertEquals(2, result.size());
        assertEquals(1L, result.get(1).getStudent().getId());
        assertEquals(1L, result.get(0).getStudent().getId());

        verify(enrollmentRepository,times(1)).findAll();

    }

}