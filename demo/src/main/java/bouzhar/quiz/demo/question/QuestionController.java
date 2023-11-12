package bouzhar.quiz.demo.question;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping(path = "getQuestions")
    public List<Question> getQuestions(){
        return questionService.getQuestions();
    }
    @PostMapping(path = "addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody @Valid QuestionDto question){
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody @Valid QuestionDto questionDto){
        questionService.updateQuestion(questionDto.getId(),questionDto);
        return ResponseEntity.ok(questionService.findById(questionDto.getId()));
    }
    @DeleteMapping(path = "{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("questionId") Long questionId){
        return questionService.deleteQuestion(questionId);
    }
}
