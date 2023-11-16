package bouzhar.quiz.demo.answer;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/answer")
public class AnswerController {
    private final AnswerServiceI answerService;

    @Autowired
    public AnswerController(AnswerServiceI answerService) {
        this.answerService = answerService;
    }
    /*
    *
    * Methods
    *
    * */
    @GetMapping(path = "getAnswers")
    public ResponseEntity<List<AnswerDto>> getAnswers(){return answerService.getAnswers();}
    @PostMapping(path = "addAnswer")
    public ResponseEntity<AnswerDto> addAnswer(@RequestBody @Valid AnswerDto answerDto){
        return answerService.addAnswer(answerDto);
    }
    @PutMapping
    public ResponseEntity<AnswerDto> updateAnswer(@RequestBody @Valid AnswerDto answerDto){
        return answerService.updateAnswer(answerDto);
    }
    @DeleteMapping(path = "{answerId}")
    public ResponseEntity<String> deleteAnswer(@PathVariable("answerId") Long answerId){
        return answerService.deleteAnswer(answerId);
    }
    @GetMapping(path = "getAnswer/{answerId}")
    public ResponseEntity<AnswerDto> getAnswer(@PathVariable("answerId") Long answerId){
        return answerService.getAnswer(answerId);
    }

}
