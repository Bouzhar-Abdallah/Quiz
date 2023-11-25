package bouzhar.quiz.demo.answer;


import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.answer.dto.AnswerSimpleDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/answer")
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

    // add new answer
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerSimpleDto addAnswer(@RequestBody @Valid AnswerSimpleDto answerSimpleDto){
        return answerService.addAnswer(answerSimpleDto);
    }

    // get answer by id
    @GetMapping(path = "{answerId}")
    @ResponseStatus(HttpStatus.OK)
    public AnswerResDto getAnswer(@PathVariable("answerId") Long answerId){
        return answerService.getAnswer(answerId);
    }

    // get all answers
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AnswerResDto> getAnswers(){return answerService.getAnswers();}

    // update answer
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AnswerResDto updateAnswer(@RequestBody @Valid AnswerSimpleDto answerSimpleDto){
        return answerService.updateAnswer(answerSimpleDto);
    }

    // delete answer
    @DeleteMapping(path = "{answerId}")
    @ResponseStatus(HttpStatus.OK)
    public AnswerResDto deleteAnswer(@PathVariable("answerId") Long answerId){
        return answerService.deleteAnswer(answerId);
    }

}
