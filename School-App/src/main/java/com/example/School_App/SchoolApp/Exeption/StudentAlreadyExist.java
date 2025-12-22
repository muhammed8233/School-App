package com.example.School_App.SchoolApp.Exeption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentAlreadyExist extends RuntimeException {
    public StudentAlreadyExist(String message) {
    super(message);
    }
}
