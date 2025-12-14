package com.example.School_App.SchoolApp.SchoolAppDto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseDtoTest {
    CourseDto request = new CourseDto();

    @Test
    void testToCheckIfCourseNameIsSet(){
        request.setCourseName("biology");
        assertEquals("biology", request.getCourseName());
    }

    @Test
    void testToCheckIfCourseCodeIsSet(){
        request.setCourseCode("bio111");
        assertEquals("bio111", request.getCourseCode());
    }

}