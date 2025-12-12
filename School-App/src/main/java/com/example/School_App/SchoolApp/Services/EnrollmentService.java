package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentRequest;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService implements EnrollmentServiceInterface{

    @Autowired
    private final EnrollmentRepository enrollmentRepository;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final CourseRepository courseRepository;


    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository,
                       CourseRepository courseRepository){
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    @Override
    public Enrollment enrollStudentInCourse(EnrollmentRequest enrollmentRequest){
        Student student = studentRepository.findById(enrollmentRequest.getStudentId()).orElseThrow(() ->
                new IllegalStateException("student with id "+ enrollmentRequest.getStudentId() + " not found"));

        Course course = courseRepository.findById(enrollmentRequest.getCourseId()).orElseThrow(()
        -> new IllegalStateException("Course with id "+ enrollmentRequest.getCourseId() +" not found"));

        boolean exists = enrollmentRepository.existsByStudentAndByCourse(student, course);
        if (exists){
            throw new RuntimeException("Student is already enrolled in this course");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getStudentsByACourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

        return enrollments;
    }
    @Override
    public List<Enrollment> getCourseByStudent(Long studentId){
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        return enrollments;
    }
}
