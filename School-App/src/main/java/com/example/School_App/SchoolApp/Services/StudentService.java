package com.example.School_App.SchoolApp.Services;

import com.example.School_App.SchoolApp.Repository.StudentRepository;
import com.example.School_App.SchoolApp.Model.Student;
import com.example.School_App.SchoolApp.SchoolAppDto.StudentDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class StudentService implements StudentServiceInterface {
    @Autowired
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }



    @Override
    public StudentDto addNewStudent(@Valid StudentDto studentDTO) {

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setClassName(studentDTO.getClassName());
         Student savedStudent = studentRepository.save(student);

         StudentDto studentDto = new StudentDto();
         studentDto.setName(student.getName());
         studentDto.setEmail(student.getEmail());
         studentDto.setClassName(student.getClassName());

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
            throw new IllegalStateException("student dto can not be empty");
        }
        List<Student> students = new ArrayList<>();

        for (StudentDto dto : studentDto){
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
                .orElseThrow(() -> new RuntimeException("Student with Id " + id + " not found"));
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

