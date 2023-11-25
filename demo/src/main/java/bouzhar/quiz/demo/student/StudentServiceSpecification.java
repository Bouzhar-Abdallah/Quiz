package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.student.dto.StudentDto;
import bouzhar.quiz.demo.student.dto.StudentResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentServiceSpecification {
    StudentResDto addNewStudent(StudentDto studentDto);

    StudentResDto updateStudent(StudentDto studentDto);

    StudentResDto deleteStudent(Long Id);

    StudentResDto getStudent(Long Id);
    List<StudentResDto> getAllStudents();

}
