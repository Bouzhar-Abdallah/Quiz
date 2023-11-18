package bouzhar.quiz.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping(path = "addStudent")
    public ResponseEntity<StudentDto> addNewStudent(@RequestBody StudentDto studentDto){
        return studentService.addNewStudent(studentDto);
    }
    @GetMapping(path = "getall")
    public ResponseEntity<List<StudentDto>> addNewStudent(){
        return studentService.getAllStudents();
    }

}
