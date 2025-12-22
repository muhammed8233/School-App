package com.example.School_App.SchoolApp.SchoolAppDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentDto {
    private Long studentId;

    @NotBlank(message = "name can not be empty")
    @Size(min =2, max = 50, message = "name size must not be > 2 or <= 50")
    private String name;

    @NotBlank(message = "email can not be empty")
    @Size(min = 3, max = 40, message = "email size can not be > 3 or <= 40")
    private String email;

    @NotBlank
    @Size(min = 3, max = 20, message = "class name must not be < 3 or > 20")
    private String className;

    public StudentDto(String name, String email, String className) {
        this.name = name;
        this.email = email;
        this.className = className;
    }

    public StudentDto() {

    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isBlank()) {
            this.email = email.toLowerCase().trim();
        }else {
            this.email = null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isBlank()) {
            this.name = name.toLowerCase().trim();
        }else {
            this.name = null;
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        if(className != null && !className.isBlank()) {
            this.className = className.toLowerCase().trim();
        }else {
            this.className = null;
        }
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
