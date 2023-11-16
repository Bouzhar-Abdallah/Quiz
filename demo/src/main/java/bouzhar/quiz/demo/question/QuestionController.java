package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.media.MediaDto;
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
    public List<QuestionDto> getQuestions(){
        return questionService.getQuestions();
    }
    @GetMapping(path = "getQuestion/{questionId}")
    public ResponseEntity<?> getQuestionBiId(@PathVariable Long questionId){
        return questionService.findById(questionId);
    }
    @PostMapping(path = "addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody @Valid QuestionDto questionDto){
        //List<MediaDto> medias = questionDto.getMedias();
        return new ResponseEntity<>(questionService.addQuestion(questionDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody @Valid QuestionDto questionDto){
        return questionService.updateQuestion(questionDto.getId(),questionDto);
    }
    @DeleteMapping(path = "{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("questionId") Long questionId){
        return questionService.deleteQuestion(questionId);
    }

}
