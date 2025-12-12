package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
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
    public void enrollStudentInCourse(Long studentId, Long courseId){
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("student with id "+ studentId + " not found"));

        Course course = courseRepository.findById(courseId).orElseThrow(()
        -> new IllegalStateException("Course with id "+ courseId +" not found"));

        boolean exists = enrollmentRepository.existsByStudentAndByCourse(student, course);
        if (exists){
            throw new RuntimeException("Student is already enrolled in this course");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
         enrollmentRepository.save(enrollment);
    }

    @Override
    public List<StudentDto> getStudentsByACourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        List<StudentDto> students = new ArrayList<>();

        for(Enrollment enrollment : enrollments){
            Student student = enrollment.getStudent();
            students.add(new StudentDto(student.getName(), student.getEmail(), student.getClassName()));
        }
        return students;
    }
    @Override
    public List<CourseRequest> getCourseByStudent(Long studentId){
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        List<CourseRequest> courses = new ArrayList<>();

        for(Enrollment enrollment : enrollments){
            Course course = enrollment.getCourse();
            courses.add(new CourseRequest(course.getCourseName(),course.getCourseCode()));
        }
        return courses;
    }
}
