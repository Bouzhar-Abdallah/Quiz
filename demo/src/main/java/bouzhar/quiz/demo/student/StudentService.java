package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.assignement.Assignement;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService implements StudentServiceSpecification {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<StudentDto> addNewStudent(@Valid StudentDto studentDto) {
        if (studentDto.getRegestrationDate() == null) studentDto.setRegestrationDate(LocalDate.now());
        return ResponseEntity.ok(modelMapper.map(studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentDto.class));
    }

    @Override
    public ResponseEntity<StudentDto> updateStudent(StudentDto studentDto) {
        studentRepository.findById(studentDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("student with id" + studentDto.getId() + "not found")
        );
        return ResponseEntity.ok(
                modelMapper.map(studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentDto.class)
        );
    }

    @Override
    public ResponseEntity<StudentDto> deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student with id" + id + "not found"));
        studentRepository.deleteById(id);
        return ResponseEntity.ok(modelMapper.map(student, StudentDto.class));
    }

    @Override
    public ResponseEntity<StudentDto> getStudent(Long id) {
        return ResponseEntity.ok(modelMapper.map(
                studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student with id" + id + "not found")),
                StudentDto.class
        ));
    }

    @Override
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList());
    }

/*    public ResponseEntity<StudentDto> getStudentLazy(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("student with id"+ id +"not found"));
        StudentDto studentDto = modelMapper.map(student,StudentDto.class);

        return
                ResponseEntity.ok(studentDto);
    }*/
}
