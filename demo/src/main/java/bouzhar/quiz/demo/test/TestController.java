package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.temporization.TemporizationReqDto;
import bouzhar.quiz.demo.test.Dtos.TestReqDto;
import bouzhar.quiz.demo.test.Dtos.TestResDto;
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
    @PostMapping
    public ResponseEntity<TestResDto> addNewTest(@RequestBody @Valid TestReqDto testResDto){
        return testService.addNewTest(testResDto);
    }
    @PostMapping(path = "addQuestion")
    public ResponseEntity<TestResDto> addNewTest(@RequestBody @Valid TemporizationReqDto temporizationReqDto){
        return testService.addQuestion(temporizationReqDto);
    }
    @GetMapping(path = "{test_id}")
    public ResponseEntity<TestResDto> getTest(@PathVariable Long test_id){
        return testService.getTest(test_id);
    }
    @GetMapping
    public ResponseEntity<List<TestResDto>> addNewTest(){
        return testService.getAllTests();
    }
    @PutMapping()
    public ResponseEntity<TestResDto> updateTest(@RequestBody TestReqDto testResDto){
        return testService.updateTest(testResDto);
    }

    @DeleteMapping(path = "{test_id}")
    public ResponseEntity<TestResDto> deleteTest(@PathVariable Long test_id){
        return testService.deleteTest(test_id);
    }

}
