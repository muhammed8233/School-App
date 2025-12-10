package com.example.School_App.Models.SchoolApp.Services;

import com.example.School_App.Models.SchoolApp.Course;
import com.example.School_App.Models.SchoolApp.Enrollment;
import com.example.School_App.Models.SchoolApp.Repository.CourseRepository;
import com.example.School_App.Models.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.Models.SchoolApp.Repository.StudentRepository;
import com.example.School_App.Models.SchoolApp.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public  Enrollment enrollStudentInCourse(Long studentId, Long courseId, Enrollment enrollment){
        Student student = studentRepository.findById(studentId).orElseThrow(()
                -> new IllegalStateException("student with id "+ studentId+" not found"));
        Course course = courseRepository.findCourseById(courseId).orElseThrow(()
                -> new IllegalStateException("course with id "+ courseId + " not found"));

        boolean exist= enrollmentRepository.existsByStudentAndByCourse(student, course);
        if (exist){
            throw new RuntimeException("Student is already enrolled in this course");
        }
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }
    public List<Student> getStudentsByCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findStudentByCourseId(courseId);
        return enrollments.stream().map(Enrollment::getStudent).collect(Collectors.toList());
    }
    public List<Course> getCourseByStudent(Long studentId){
        List<Enrollment> enrollments = enrollmentRepository.findCourseByStudentId(studentId);
        return enrollments.stream().map(Enrollment::getCourse).collect(Collectors.toList());
    }
}
