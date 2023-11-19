package bouzhar.quiz.demo.student;

import jakarta.validation.constraints.NotNull;
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
    @GetMapping(path = "{student_id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long student_id){
        return studentService.getStudent(student_id);
    }
    @DeleteMapping(path = "{student_id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Long student_id){
        return studentService.deleteStudent(student_id);
    }
    @PutMapping()
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto){
        return studentService.updateStudent(studentDto);
    }

}
