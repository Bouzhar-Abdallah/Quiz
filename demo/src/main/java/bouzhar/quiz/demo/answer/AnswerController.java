package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
    @GetMapping(path = "getAnswers")
    public List<Answer> getAnswers(){return answerService.getAnswers();}
    @PostMapping(path = "addAnswer")
    public Answer addAnswer(@RequestBody Answer answer){
        return answerService.addAnswer(answer);
    }

}
