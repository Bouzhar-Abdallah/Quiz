package bouzhar.quiz.demo.teacher;

import bouzhar.quiz.demo.teacher.Dto.TeacherDto;
import bouzhar.quiz.demo.teacher.Dto.TeacherResDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherServiceSpecification {
    TeacherResDto addNewTeacher(TeacherDto teacherDto);

    TeacherResDto updateTeacher(TeacherDto teacherDto);

    TeacherResDto deleteTeacher(Long Id);

    TeacherResDto getTeacher(Long Id);
    List<TeacherResDto> getAllTeachers();

    Page<TeacherResDto> getPaginatedAnswers(int page, int size);
}
