package bouzhar.quiz.demo.teacher;


import bouzhar.quiz.demo.teacher.Dto.TeacherDto;
import bouzhar.quiz.demo.teacher.Dto.TeacherResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /*
     *
     * Methods
     *
     * */

    // add new teacher
    @PostMapping
    public TeacherResDto addNewTeacher(@RequestBody @Valid TeacherDto teacherDto) {
        return teacherService.addNewTeacher(teacherDto);
    }

    // get teacher
    @GetMapping(path = "{teacher_id}")
    public TeacherResDto getTeacher(@PathVariable Long teacher_id) {
        return teacherService.getTeacher(teacher_id);
    }

    // get all teacher
    @GetMapping
    public List<TeacherResDto> addAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // update teacher
    @PutMapping()
    public TeacherResDto updateTeacher(@RequestBody TeacherDto teacherDto) {
        return teacherService.updateTeacher(teacherDto);
    }

    // delete teacher
    @DeleteMapping(path = "{teacher_id}")
    public TeacherResDto deleteTeacher(@PathVariable Long teacher_id) {
        return teacherService.deleteTeacher(teacher_id);
    }
}
