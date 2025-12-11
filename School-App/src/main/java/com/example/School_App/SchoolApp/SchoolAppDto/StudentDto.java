package com.example.School_App.SchoolApp.SchoolAppDto;

public class StudentDto {
    private String name;
    private String email;
    private String className;

    public StudentDto(String name, String email, String className) {
        this.name = name;
        this.email = email;
        this.className = className;
    }

    public StudentDto() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
