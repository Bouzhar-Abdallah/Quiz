package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.student.dto.StudentDto;
import bouzhar.quiz.demo.student.dto.StudentResDto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    /*
     *
     * Methods
     *
     * */

    // Add new student
    @Override
    public StudentResDto addNewStudent(@Valid StudentDto studentDto) {
        if (studentDto.getRegistrationDate() == null) studentDto.setRegistrationDate(LocalDate.now());
        return modelMapper.map(studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentResDto.class);
    }

    // Get student by id
    @Override
    public StudentResDto getStudent(Long id) {
        return modelMapper.map(
                studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student with id" + id + "not found")),
                StudentResDto.class
        );
    }

    // Get all students
    @Override
    public List<StudentResDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentResDto.class))
                .toList();
    }

    // Update student
    @Override
    public StudentResDto updateStudent(StudentDto studentDto) {
        studentRepository.findById(studentDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("student with id" + studentDto.getId() + "not found")
        );
        return
                modelMapper.map(studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentResDto.class)
                ;
    }

    // Delete student
    @Override
    public StudentResDto deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student with id" + id + "not found"));
        StudentResDto deletedStudent = modelMapper.map(student, StudentResDto.class);
        studentRepository.deleteById(id);
        return deletedStudent;
    }

    @Override
    public Page<StudentResDto> getPaginatedAnswers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return studentRepository.findAll(pageRequest).map(student -> modelMapper.map(student, StudentResDto.class));
    }
}
