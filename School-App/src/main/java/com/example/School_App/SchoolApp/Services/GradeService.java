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
import com.example.School_App.SchoolApp.SchoolAppDto.GradeRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.ScoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService implements GradeServiceInterface {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final GradeRepository gradeRepository;
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final EnrollmentRepository enrollmentRepository;

    public GradeService(CourseRepository courseRepository,
                        StudentRepository studentRepository,
                        GradeRepository gradeRepository,
                        EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void recordStudentScore(Long studentId, Long courseId, Assessment type) {
        Student student = studentRepository.findById(studentId).orElseThrow(()
                -> new RuntimeException("Student with Id "+ studentId + "not found") );
        Course course = courseRepository.findById(courseId).orElseThrow(()
                -> new RuntimeException("course with id "+ courseId + " not found"));

        Enrollment enrollment = enrollmentRepository.findByStudentAndCourse(student, course);
        if(gradeRepository.existsByEnrollment(enrollment)){
            throw new RuntimeException("Enrollment not found");
        }
        Optional<Enrollment> existingEnrollment = gradeRepository.
                existsByEnrollmentAndAssessmentType(enrollment, type);
        if (existingEnrollment.isPresent()){
            throw new RuntimeException(" the student score has been recorded");
        }

        Grade grade = new Grade();
        grade.setEnrollmentId(enrollment);
        grade.setAssessmentType(type);
        gradeRepository.save(grade);

    }

    @Override
    public ScoreDto getAllStudentScoreInACourse(Long studentId, Long courseId) {
        Double exam = gradeRepository.findById(studentId, courseId, Assessment.EXAM)
                .orElseThrow(()-> new RuntimeException
                ("exam record not found ")).getScore();
        Double test = gradeRepository.findById(studentId, courseId, Assessment.TEST).orElseThrow(()
                -> new RuntimeException("test record not found")).getScore();
        Double assignment = gradeRepository.findById(studentId, courseId, Assessment.ASSIGNMENT).orElseThrow(()
        -> new RuntimeException("assignment record not found")).getScore();

        return new ScoreDto(studentId, courseId, exam, test, assignment);
    }


    @Override
    public double computeFinalScore(Long enrollmentId){
        List<Grade> grades = gradeRepository.findByEnrollmentId(enrollmentId);
        double testScore = 0;
        double examScore = 0;

        for(Grade grade : grades){
            if(grade.getAssessmentType() == Assessment.TEST){
                testScore = grade.getScore();
            }else if(grade.getAssessmentType() == Assessment.EXAM){
                examScore = grade.getScore();
            }
        }
        return 0.4 * testScore + 0.6 * examScore;

    }

}
