package bouzhar.quiz.demo.student;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentServiceSpecification {
    ResponseEntity<StudentDto> addNewStudent(StudentDto studentDto);

    ResponseEntity<StudentDto> updateStudent(Long id, StudentDto studentDto);

    ResponseEntity<StudentDto> deleteStudent(Long Id);

    ResponseEntity<StudentDto> getStudent(Long Id);
    ResponseEntity<List<StudentDto>> getAllStudents();

}
