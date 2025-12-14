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
import com.example.School_App.SchoolApp.SchoolAppDto.CourseDto;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeDto;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
    void testToSetGradeOfAStudent() {
        List<Student> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        List<EnrollmentDto> enrollmentRequests = List.of(
                new EnrollmentDto(savedStudent.get(0).getId(), savedCourse.get(0).getId()));

        enrollmentService.saveAllEnrollments(enrollmentRequests);

        int expectedScore = 50;
        Assessment assessmentType = Assessment.TEST;

        Grade grade = gradeService.recordStudentScore(savedStudent.get(0).getId(), savedCourse.get(0).getId(),
                assessmentType,
                expectedScore
        );

        assertNotNull(grade);
        assertNotNull(grade.getId());

        assertEquals(expectedScore, grade.getScore());
        assertEquals(Assessment.TEST, grade.getAssessmentType());
        assertEquals(savedStudent.get(0).getId(), grade.getEnrollment().getStudent().getId());
    }


    @Test
    void testToRecordStudentScoreSuccessfully() {
       List<Student> savedStudent = studentService.saveAllStudents(List.of(new StudentDto("musa", "musa@gmail.com", "ss1")));
        List<Course> savedCourse = courseService.saveAllCoursesFromDto(List.of(new CourseDto("physics", "phy101")));

        enrollmentService.enrollStudentInCourse(savedStudent.get(0).getId(), savedCourse.get(0).getId());

        int expectedScore = 50;
        Assessment assessmentType = Assessment.TEST;

        Grade recordedGrade = gradeService.recordStudentScore(savedStudent.get(0).getId(),
                savedCourse.get(0).getId(), assessmentType, expectedScore);

        assertNotNull(recordedGrade);
        assertNotNull(recordedGrade.getId());
        assertEquals(expectedScore, recordedGrade.getScore());
        assertEquals(Assessment.TEST, recordedGrade.getAssessmentType());

        Optional<Grade> foundGrade = gradeRepository.findById(recordedGrade.getId());
        assertTrue(foundGrade.isPresent());
        assertEquals(savedStudent.get(0).getId(), foundGrade.get().getEnrollment().getStudent().getId());
    }


}





