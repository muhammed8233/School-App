package com.example.School_App.Models.SchoolApp.Controller;

import com.example.School_App.Models.SchoolApp.Course;
import com.example.School_App.Models.SchoolApp.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getStudentCourse(){
        return courseService.getStudentCourse();
    }

    @PostMapping
    public void addNewCourse(@RequestBody Course course){
         courseService.addNewCourse(course);
    }
}
