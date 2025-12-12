package com.example.School_App.SchoolApp.SchoolAppDto;

import com.example.School_App.SchoolApp.Model.Grade;
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
        gradeRequest.setScore(20.0);
        assertEquals(20.0, gradeRequest.getScore());
    }

    @Test
    void verifyIfAssignmentScoreIsSet(){
        Grade grade = new Grade();
        grade.setScore(12.0);
        gradeRequest.setAssignmentScore(grade.getScore());
        assertEquals(12.0, gradeRequest.getAssignmentScore());
    }

    @Test
    void verifyTestScoreIsSet(){
        Grade grade = new Grade();
        grade.setScore(11);
        gradeRequest.setTestScore(grade.getScore());
        assertEquals(11, gradeRequest.getTestScore());
    }

    @Test
    void verifyExamScoreIsSet(){
        Grade grade = new Grade();
        grade.setScore(22.1);
        gradeRequest.setExamScore(grade.getScore());
        assertEquals(22.1, gradeRequest.getExamScore());
    }

}