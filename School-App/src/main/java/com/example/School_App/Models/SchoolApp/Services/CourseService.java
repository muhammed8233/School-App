package com.example.School_App.Models.SchoolApp.Services;

import com.example.School_App.Models.SchoolApp.Course;
import com.example.School_App.Models.SchoolApp.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getStudentCourse(){
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course){
        Optional<Course> optional = courseRepository.findById(course.getCourseId());
        if(optional.isPresent()){
            throw new IllegalStateException("Course already exist");
        }
    }

}
