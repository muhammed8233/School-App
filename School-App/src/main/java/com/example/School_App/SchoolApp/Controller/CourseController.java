package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.Model.Course;
import com.example.School_App.SchoolApp.SchoolAppDto.CourseDto;
import com.example.School_App.SchoolApp.Services.CourseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    @Autowired
    private final CourseServiceInterface courseServiceInterface;


    public CourseController(CourseServiceInterface courseServiceInterface){
        this.courseServiceInterface = courseServiceInterface;
    }

    @GetMapping
    public List<CourseDto> getStudentCourse(){
        return courseServiceInterface.getStudentCourse();
    }

    @PostMapping
    public Course addNewCourse(@RequestBody CourseDto courseDto){
        return courseServiceInterface.addNewCourse(courseDto);
    }

    @PostMapping("save")
    public List<Course> uploadCourses(@RequestBody List<CourseDto> courseDtoList) {
        return courseServiceInterface.saveAllCoursesFromDto(courseDtoList);
    }

}
