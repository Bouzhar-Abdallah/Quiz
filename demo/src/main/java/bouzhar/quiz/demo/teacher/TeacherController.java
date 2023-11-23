package bouzhar.quiz.demo.teacher;


import bouzhar.quiz.demo.teacher.Dto.TeacherDto;
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
    @PostMapping
    public ResponseEntity<TeacherDto> addNewTeacher(@RequestBody @Valid TeacherDto teacherDto){
        return teacherService.addNewTeacher(teacherDto);
    }
    @GetMapping
    public ResponseEntity<List<TeacherDto>> addNewTeacher(){
        return teacherService.getAllTeachers();
    }
    @GetMapping(path = "{teacher_id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long teacher_id){
        return teacherService.getTeacher(teacher_id);
    }
    @DeleteMapping(path = "{teacher_id}")
    public ResponseEntity<TeacherDto> deleteTeacher(@PathVariable Long teacher_id){
        return teacherService.deleteTeacher(teacher_id);
    }
    @PutMapping()
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto){
        return teacherService.updateTeacher(teacherDto);
    }
}
