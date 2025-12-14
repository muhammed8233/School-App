package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Model.Enrollment;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.Repository.EnrollmentRepository;
import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.EnrollmentDto;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public List<Enrollment> saveAllEnrollments(List<EnrollmentDto> enrollmentDto){
       if(enrollmentDto == null || enrollmentDto.isEmpty()){
           throw new IllegalStateException("enrollment can not be empty");
       }
       List<Enrollment> enrollments = new ArrayList<>();

       for(EnrollmentDto dto : enrollmentDto){
            Enrollment enrollment = enrollStudentInCourse(dto.getStudentId(), dto.getCourseId());
            enrollments.add(enrollment);
       }
       return enrollments;
    }

    public List<EnrollmentDto> getAllEnrollment(){
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<EnrollmentDto> enrollmentDto = new ArrayList<>();

        for(Enrollment enrollment : enrollments){
            enrollmentDto.add(new EnrollmentDto(enrollment.getStudent().getId(),
                    enrollment.getCourse().getId()));
        }
        return enrollmentDto;
    }

        @Override
        @Transactional
        public Enrollment enrollStudentInCourse(Long studentId, Long courseId){
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " not found"));
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new IllegalStateException("Course with id " + courseId + " not found"));

            if (enrollmentRepository.existsByStudentAndCourse(student, course)){
                throw new RuntimeException("Student is already enrolled in this course");
            }

            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            return enrollmentRepository.save(enrollment);
        }

    @Override
    public List<EnrollmentDto> getStudentsByACourse(Long courseId) {
        return List.of();
    }

    @Override
    public List<EnrollmentDto> getCourseByStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        List<EnrollmentDto> enrollmentDtoList = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            enrollmentDtoList.add(new EnrollmentDto(
                    enrollment.getId(),
                    enrollment.getStudent().getId(),
                    enrollment.getCourse().getId()
            ));
        }
        return enrollmentDtoList;
    }



}
