package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.GradeRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.parser.Entity;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradeServiceTest {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EnrollmentService enrollmentService;


    @BeforeEach
    void setup() {
        gradeRepository.deleteAll();
    }

    @Test
    void testToRecordStudentGradeInACourse() {
        List<Student> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        List<EnrollmentDto> enrollmentRequests = List.of(
                new EnrollmentDto(savedStudent.get(0).getId(), savedCourse.get(0).getId()));

        enrollmentService.saveAllEnrollments(enrollmentRequests);


        Grade grade = gradeService.recordStudentScore(savedStudent.get(0).getId(),
                savedCourse.get(0).getId(), Assessment.TEST, 50);

        assertNotNull(grade);
        assertNotNull(grade.getId());
        assertEquals(50, grade.getScore());
        assertEquals(Assessment.TEST, grade.getAssessmentType());
    }

    @Transactional
    @Test
    void testToComputeFinalScoreForStudent() {

        List<Student> savedStudents = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourses = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));
        List<Enrollment> enrollments = enrollmentService.saveAllEnrollments(List.of(
                    new EnrollmentDto(savedStudents.get(0).getId(), savedCourses.get(0).getId())));

        gradeService.saveAllGradesFromDto(List.of(
                new GradeDto(savedStudents.get(0).getId(), savedCourses.get(0).getId(), Assessment.TEST, 50.0),
                new GradeDto(savedStudents.get(0).getId(), savedCourses.get(0).getId(), Assessment.EXAM, 90.0)
        ));


        double result = gradeService.computeFinalScore(enrollments.get(0).getId());

        assertNotNull(result);
        assertEquals(74.0, result, 0.01);
    }

    @Test
    void testToGetAllStudentScoreInAcourse(){
        List<Student> savedStudents = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourses = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));
        List<Enrollment> enrollments = enrollmentService.saveAllEnrollments(List.of(
                new EnrollmentDto(savedStudents.get(0).getId(), savedCourses.get(0).getId())));

        gradeService.saveAllGradesFromDto(List.of(
                new GradeDto(savedStudents.get(0).getId(), savedCourses.get(0).getId(), Assessment.TEST, 50.0),
                new GradeDto(savedStudents.get(0).getId(), savedCourses.get(0).getId(), Assessment.EXAM, 90.0)
        ));
       List<ScoreDto>  result = gradeService.getAllStudentScoreInACourse();

       assertNotNull(result);
       assertEquals(1, result.size());
       assertEquals(90, result.get(0).getExamScore());
    }


}







