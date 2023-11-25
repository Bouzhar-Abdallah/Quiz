package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.student.dto.StudentDto;
import bouzhar.quiz.demo.student.dto.StudentResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
     *
     * Methods
     *
     * */

    // add new student
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResDto addNewStudent(@RequestBody StudentDto studentDto) {
        return studentService.addNewStudent(studentDto);
    }

    // get student by id
    @GetMapping(path = "{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResDto getStudent(@PathVariable Long student_id) {
        return studentService.getStudent(student_id);
    }

    // get all students
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResDto> getStudents() {
        return studentService.getAllStudents();
    }

    // update student
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public StudentResDto updateStudent(@RequestBody StudentDto studentDto) {
        return studentService.updateStudent(studentDto);
    }

    // delete student
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "{student_id}")
    public StudentResDto deleteStudent(@PathVariable Long student_id) {
        return studentService.deleteStudent(student_id);
    }

}
