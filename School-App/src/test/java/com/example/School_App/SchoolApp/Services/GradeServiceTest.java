package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.Repository.GradeRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradeServiceTest {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setup() {
        gradeRepository.deleteAll();
    }

    @Test
    void testToSetGradeOfAStudent() {
        Student student = new Student("musa", "musa@gmail.com", "ss1");
        studentRepository.save(student);
        Course course = new Course("physics", "phy101");
        courseRepository.save(course);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);
        Grade testGrade = new Grade();
        testGrade.setAssessmentType(Assessment.TEST);
        testGrade.setScore(50);
        testGrade.setEnrollment(enrollment);


        Grade grade = gradeService.recordStudentScore(testGrade.getEnrollment().getStudent().getId(),
                testGrade.getEnrollment().getCourse().getId(), testGrade.getAssessmentType(), testGrade.getScore());

        assertNotNull(grade);
        assertEquals(1, grade.getEnrollment().getStudent().getId());
        assertEquals(50, grade.getScore());
        assertEquals(Assessment.TEST, grade.getAssessmentType());


    }
}


