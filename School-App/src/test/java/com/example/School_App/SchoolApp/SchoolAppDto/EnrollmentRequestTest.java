package com.example.School_App.SchoolApp.SchoolAppDto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentRequestTest {
    EnrollmentRequest request = new EnrollmentRequest();

    @Test
    void testToVerifyStudentIdIsSet(){
        request.setStudentId(1L);
        assertEquals(1L, request.getStudentId());
    }

    @Test
    void testToVerifyCourseIdIsSet(){
        request.setCourseId(2L);
        assertEquals(2L,request.getCourseId());
    }

}