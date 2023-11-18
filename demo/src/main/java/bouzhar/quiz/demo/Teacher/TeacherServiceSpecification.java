package bouzhar.quiz.demo.Teacher;

import bouzhar.quiz.demo.student.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherServiceSpecification {
    ResponseEntity<TeacherDto> addNewTeacher(TeacherDto teacherDto);

    ResponseEntity<TeacherDto> updateTeacher(TeacherDto teacherDto);

    ResponseEntity<TeacherDto> deleteTeacher(Long Id);

    ResponseEntity<TeacherDto> getTeacher(Long Id);
    ResponseEntity<List<TeacherDto>> getAllTeachers();
}
