package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Enum.Assessment;
import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Grade;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.GradeRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.GradeDto;
import com.example.School_App.SchoolApp.SchoolAppDto.ScoreDto;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService implements GradeServiceInterface {

    @Autowired private final GradeRepository gradeRepository;
    @Autowired private final StudentService studentService;
    @Autowired private final CourseService courseService;
    @Autowired private final EnrollmentService enrollmentService;

    public GradeService(StudentService studentService,
                        CourseService courseService,
                        GradeRepository gradeRepository,
                        EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeRepository = gradeRepository;
        this.enrollmentService = enrollmentService;
    }

    @Override
    public Grade recordStudentScore(Long studentId, Long courseId, Assessment type, double score) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        Enrollment enrollment = enrollmentService.findEnrollmentByStudentAndCourse(student, course); // Assumed service method

        boolean existingEnrollment = gradeRepository.
                existsByEnrollmentAndAssessmentType(enrollment, type);
        if (existingEnrollment){
            throw new RuntimeException(" the student score has been recorded for this assessment type");
        }

        Grade grade = new Grade();
        grade.setEnrollment(enrollment);
        grade.setAssessmentType(type);
        grade.setScore(score);
        return gradeRepository.save(grade);
    }
    @Override
    public List<ScoreDto> getAllStudentScoreInACourse() {
        List<EnrollmentDto> enrollments = enrollmentService.getAllEnrollment();
        List<ScoreDto> results = new ArrayList<>();

        for (EnrollmentDto enrollment : enrollments) {
            ScoreDto dto = new ScoreDto();
            dto.setStudentId(enrollment.getStudentId());
            dto.setCourseId(enrollment.getCourseId());

            Double testVal = gradeRepository.findScore(enrollment.getEnrollmentId(), Assessment.TEST);
            Double assignmentVal = gradeRepository.findScore(enrollment.getEnrollmentId(), Assessment.ASSIGNMENT);
            Double examVal = gradeRepository.findScore(enrollment.getEnrollmentId(), Assessment.EXAM);

            dto.setTestScore(testVal);
            dto.setAssignmentScore(assignmentVal);
            dto.setExamScore(examVal);

            double finalScore = computeFinalScore(enrollment.getEnrollmentId());
            dto.setFinalScore(finalScore);

            results.add(dto);
        }
        return results;
    }

    @Override
    public double computeFinalScore(Long enrolmentId){
        List<Grade> grades = gradeRepository.findByEnrollmentId(enrolmentId);
        if(grades == null || grades.isEmpty()){
            return 0.0;
        }
        double testScore = 0;
        double examScore = 0;

        for(Grade grade : grades){
            System.out.println("Grade Type: " + grade.getAssessmentType() + " Score: " + grade.getScore());
            if(grade.getAssessmentType() == Assessment.TEST){
                testScore = grade.getScore();
            }else if(grade.getAssessmentType() == Assessment.EXAM){
                examScore = grade.getScore();
            }
        }

        double result = 0.4 * testScore + 0.6 * examScore;

        return result;
    }

    @Transactional
    @Override
    public List<GradeDto> saveAllGradesFromDto(List<GradeDto> gradeRequests) {
        if (gradeRequests == null || gradeRequests.isEmpty()) {
            throw new IllegalArgumentException("Grade list cannot be empty.");
        }
        List<Grade> savedGrades = new ArrayList<>();

        for (GradeDto request : gradeRequests) {
            Grade savedGrade = recordStudentScore(request.getStudentId(),
                    request.getCourseId(), request.getAssessmentType(), request.getScore());

            savedGrades.add(savedGrade);
        }
        List<Grade> grades = gradeRepository.saveAll(savedGrades);
        List<GradeDto> dtos = new ArrayList<>();

        for (Grade grade : grades){
            GradeDto gradeDto = new GradeDto();
            gradeDto.setStudentId(grade.getEnrollment().getStudent().getId());
            gradeDto.setCourseId(grade.getEnrollment().getCourse().getId());
            gradeDto.setScore(grade.getScore());
            gradeDto.setAssessmentType(grade.getAssessmentType());
            dtos.add(gradeDto);
        }
        return dtos;
    }
}
