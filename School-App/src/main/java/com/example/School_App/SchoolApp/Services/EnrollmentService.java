package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Course;
import com.example.School_App.SchoolApp.Enrollment;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository,
                       CourseRepository courseRepository){
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public  Enrollment enrollStudentInCourse(Long studentId, Long courseId){
        Student student = studentRepository.findById(studentId).orElseThrow(()
                -> new IllegalStateException("student with id "+ studentId+" not found"));
        Course course = courseRepository.findCourseById(courseId).orElseThrow(()
                -> new IllegalStateException("course with id "+ courseId + " not found"));

        boolean exist= enrollmentRepository.existsByStudentAndByCourse(student, course);
        if (exist){
            throw new RuntimeException("Student is already enrolled in this course");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }
    public List<Enrollment> getEnrollmentRepository() {
        return enrollmentRepository.findAll();
    }
}
