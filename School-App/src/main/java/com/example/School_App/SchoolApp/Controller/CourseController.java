package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import com.example.School_App.SchoolApp.Services.CourseService;
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
    public List<CourseRequest> getStudentCourse(){
        return courseService.getStudentCourse();
    }

    @PostMapping
    public void addNewCourse(@RequestBody CourseRequest courseRequest){
         courseService.addNewCourse(courseRequest);
    }
}
