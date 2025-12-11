package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.Repository.CourseRepository;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseService implements CourseServiceInterface {
    @Autowired
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseRequest> getStudentCourse(){
        List<Course> courses = courseRepository.findAll();
        List<CourseRequest> courseRequests = new ArrayList<>();

        for(Course course : courses){
            CourseRequest courseRequest = new CourseRequest();
            courseRequest.setCourseName(course.getCourseName());
            courseRequest.setCourseCode(course.getCourseCode());
        }
        return courseRequests;
    }

    @Override
    public void addNewCourse(CourseRequest courseRequest){
        boolean exists = courseRepository.existsByCourseCode(courseRequest.getCourseCode());
        if(exists){
            throw new IllegalStateException("Course already exist");
        }
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setCourseCode(courseRequest.getCourseCode());
         courseRepository.save(course);
    }

}
