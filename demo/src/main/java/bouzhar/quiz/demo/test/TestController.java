package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.temporization.TemporizationReqDto;
import bouzhar.quiz.demo.test.Dtos.TestReqDto;
import bouzhar.quiz.demo.test.Dtos.TestResDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v2/test")
@RestController
@AllArgsConstructor
public class TestController {
    private final TestService testService;

    /*
     *
     * Methods
     *
     * */

    // Add new test
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TestResDto addNewTest(@RequestBody @Valid TestReqDto testResDto) {
        return testService.addNewTest(testResDto);
    }

    // Get test by id
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{test_id}")
    public TestResDto getTest(@PathVariable Long test_id) {
        return testService.getTest(test_id);
    }

    // Get all tests
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<TestResDto> getAllTests() {
        return testService.getAllTests();
    }

    // Update test
    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public TestResDto updateTest(@RequestBody TestReqDto testResDto) {
        return testService.updateTest(testResDto);
    }

    // Delete test
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "{test_id}")
    public TestResDto deleteTest(@PathVariable Long test_id) {
        return testService.deleteTest(test_id);
    }

    // Add question to test
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "addQuestion")
    public TestResDto addQuestionToTest(@RequestBody @Valid TemporizationReqDto temporizationReqDto) {
        return testService.addQuestion(temporizationReqDto);
    }

    @GetMapping("/pages")
    public Page<TestResDto> getPaginatedAnswers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return testService.getPaginatedAnswers(page, size);
    }
}
