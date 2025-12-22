package com.example.school_app.schoolApp.services;

import com.example.school_app.schoolApp.exception.StudentAlreadyExistException;
import com.example.school_app.schoolApp.exception.StudentNotFoundException;
import com.example.school_app.schoolApp.repository.StudentRepository;
import com.example.school_app.schoolApp.model.Student;
import com.example.school_app.schoolApp.dto.StudentDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements StudentServiceInterface {
    @Autowired
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }



    @Override
    public StudentDto addNewStudent( StudentDto studentDTO) {
        boolean exists = studentRepository.existsByEmail(studentDTO.getEmail());
        if(exists){
            throw new StudentAlreadyExistException("student with email:" + studentDTO.getEmail() + " already exist");

        }
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setClassName(studentDTO.getClassName());
         Student savedStudent = studentRepository.save(student);

         StudentDto studentDto = new StudentDto();
         studentDto.setStudentId(savedStudent.getId());
         studentDto.setName(savedStudent.getName());
         studentDto.setEmail(savedStudent.getEmail());
         studentDto.setClassName(savedStudent.getClassName());

         return studentDto;

    }

    @Override
    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDto = new ArrayList<>();

        for (Student student : students){
            studentDto.add(new StudentDto(student.getName(),
                    student.getEmail(), student.getClassName()));
        }

        return studentDto;
    }

    @Transactional
    @Override
    public List<StudentDto> saveAllStudents(List<StudentDto> studentDto){
        if(studentDto == null || studentDto.isEmpty() ){
            throw new StudentNotFoundException("student dto can not be empty");
        }
        List<Student> students = new ArrayList<>();

        for (StudentDto dto : studentDto){
            if(studentRepository.existsByEmail(dto.getEmail())){
                throw new StudentAlreadyExistException("Email " + dto.getEmail() + " already exists");
            }
            Student student = new Student(dto.getName(), dto.getEmail(), dto.getClassName());
            students.add(student);
        }

        List<Student> savedStudents = studentRepository.saveAll(students);

        List<StudentDto> dtos = new ArrayList<>();
        for(Student student : savedStudents){
            StudentDto studentDto1 = new StudentDto();
            studentDto1.setStudentId(student.getId());
            studentDto1.setName(student.getName());
            studentDto1.setEmail(student.getEmail());
            studentDto1.setClassName(student.getClassName());
            dtos.add(studentDto1);
        }
        return dtos;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with Id " + id + " not found"));
    }

    public String findNameById(Long id) {
        return getStudentById(id).getName();
    }

    private List<StudentDto> getAllStudentAsDto(){
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDto = new ArrayList<>();

        for(Student student : students){
            studentDto.add(new StudentDto(student.getName(), student.getEmail(), student.getClassName()));
        }

        return studentDto;
    }

}

