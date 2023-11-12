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
}
