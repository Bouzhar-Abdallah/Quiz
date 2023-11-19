package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.teacher.TeacherDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/test")
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }
    @PostMapping(path = "addTest")
    public ResponseEntity<TestDto> addNewTest(@RequestBody @Valid TestDto testDto){
        return testService.addNewTest(testDto);
    }
    /*@GetMapping(path = "getall")
    public ResponseEntity<List<TestDto>> addNewTest(){
        return testService.getAllTests();
    }
    @GetMapping(path = "{teacher_id}")
    public ResponseEntity<TestDto> getTest(@PathVariable Long teacher_id){
        return testService.getTest(teacher_id);
    }
    @DeleteMapping(path = "{teacher_id}")
    public ResponseEntity<TestDto> deleteTest(@PathVariable Long teacher_id){
        return testService.deleteTest(teacher_id);
    }
    @PutMapping()
    public ResponseEntity<TestDto> updateTest(@RequestBody TestDto testDto){
        return testService.updateTest(testDto);
    }*/
}
