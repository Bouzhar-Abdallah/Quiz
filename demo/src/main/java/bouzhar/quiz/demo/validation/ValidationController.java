package bouzhar.quiz.demo.validation;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/validation")
public class ValidationController {
    private final ValidationService validationService;

    @Autowired
    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping(path = "getValidations")
    public ResponseEntity<List<Validation>> getValidations() {
        return new ResponseEntity<>(validationService.getValidations(), HttpStatus.OK);
    }

    @PostMapping(path = "addValidation/{questionId}/{isCorrect}")
    public ResponseEntity<Validation> addValidation(
            @PathVariable("questionId") Long questionId,
            @PathVariable("isCorrect") Boolean isCorrect,
            @RequestBody Answer answer
    ) {
        return new ResponseEntity<>(validationService.addValidation(questionId, answer, isCorrect), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{questionId}/{answerId}")
    public ResponseEntity<String> deleteValidation(
            @PathVariable("questionId") Long questionId,
            @PathVariable("answerId") Long answerId) {

        validationService.deleteValidation(questionId, answerId);
        return new ResponseEntity<>("Validation deleted successfully", HttpStatus.OK);
    }
}
