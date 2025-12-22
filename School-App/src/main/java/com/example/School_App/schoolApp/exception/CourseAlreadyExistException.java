package com.example.School_App.schoolApp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CourseAlreadyExistException extends RuntimeException {
    public CourseAlreadyExistException(String message) {
        super(message);
    }
}
