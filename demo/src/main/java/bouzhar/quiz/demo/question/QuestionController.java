package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.question.dto.QuestionReqDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /*
    *
    * Methods
    *
    * */

    // Add a question
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResDto addQuestion(@RequestBody @Valid QuestionReqDto questionReqDto) {
        return questionService.addQuestion(questionReqDto);
    }
    // Get a question by id
    @GetMapping(path = "{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionResDto getQuestion(@PathVariable Long questionId) {
        return questionService.getQuestion(questionId);
    }
    // Get all questions
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionResDto> getQuestions() {
        return questionService.getQuestions();
    }
    // Update a question
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionResDto updateQuestion(@RequestBody @Valid QuestionReqDto questionReqDto) {
        return questionService.updateQuestion(questionReqDto.getId(), questionReqDto);
    }
    // Delete a question
    @DeleteMapping(path = "{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionResDto deleteQuestion(@PathVariable("questionId") Long questionId) {
        return questionService.deleteQuestion(questionId);
    }

}
