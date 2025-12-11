package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.SchoolAppDto.CourseRequest;
import com.example.School_App.SchoolApp.Services.CourseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "course")
public class CourseController {
    private final CourseServiceInterface courseServiceInterface;

    @Autowired
    public CourseController(CourseServiceInterface courseServiceInterface){
        this.courseServiceInterface = courseServiceInterface;
    }

    @GetMapping
    public List<CourseRequest> getStudentCourse(){
        return courseServiceInterface.getStudentCourse();
    }

    @PostMapping
    public void addNewCourse(@RequestBody CourseRequest courseRequest){
         courseServiceInterface.addNewCourse(courseRequest);
    }
}
