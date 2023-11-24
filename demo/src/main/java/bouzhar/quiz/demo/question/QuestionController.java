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

    @GetMapping
    public List<QuestionResDto> getQuestions() {
        return questionService.getQuestions();
    }

    @GetMapping(path = "{questionId}")
    public ResponseEntity<?> getQuestionBiId(@PathVariable Long questionId) {
        return questionService.findById(questionId);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addQuestion(@RequestBody @Valid QuestionReqDto questionReqDto) {
        return questionService.addQuestion(questionReqDto);
        //return new ResponseEntity<>(questionService.addQuestion(questionReqDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<QuestionResDto> updateQuestion(@RequestBody @Valid QuestionReqDto questionReqDto) {
        return questionService.updateQuestion(questionReqDto.getId(), questionReqDto);
    }

    @DeleteMapping(path = "{questionId}")
    public ResponseEntity<QuestionResDto> deleteQuestion(@PathVariable("questionId") Long questionId) {
        return questionService.deleteQuestion(questionId);
    }

}
