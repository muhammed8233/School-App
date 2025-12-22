package com.example.School_App.SchoolApp.Controller;

import com.example.School_App.SchoolApp.SchoolAppDto.CourseDto;
import com.example.School_App.SchoolApp.Services.CourseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
@Validated
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
    public ResponseEntity<String> addNewCourse(@RequestBody CourseDto courseDto){
         courseServiceInterface.addNewCourse(courseDto);
         return ResponseEntity.ok("Course registered successfully");
    }

    @PostMapping
    public ResponseEntity<List<CourseDto>> uploadCourses(@RequestBody List<CourseDto> courseDtoList) {
        List<CourseDto> savedCourse = courseServiceInterface.saveAllCourses(courseDtoList);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PostMapping("/bulk-register")
    public ResponseEntity<List<CourseDto>> multipleRegister(@RequestBody List<CourseDto> courseDtoList){
        List<CourseDto> savedCourses = courseServiceInterface.saveAllCourses(courseDtoList);
        return new ResponseEntity<>(savedCourses, HttpStatus.CREATED);

    }



}
