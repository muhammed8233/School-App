package com.example.School_App.SchoolApp.SchoolAppDto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeRequestTest {
    GradeRequest gradeRequest = new GradeRequest();

    @Test
    void testToVerifyStudentIdIsSet(){
        gradeRequest.setStudentId(1L);
        assertEquals(1L, gradeRequest.getStudentId());
    }

    @Test
    void testToVerifyCourseIdIsSet(){
        gradeRequest.setCourseId(2L);
        assertEquals(2L, gradeRequest.getCourseId());
    }

    @Test
    void testToVerifyStudentScoreIsSet(){

    }

}