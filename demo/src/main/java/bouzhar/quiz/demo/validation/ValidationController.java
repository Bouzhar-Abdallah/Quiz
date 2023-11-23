package bouzhar.quiz.demo.validation;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.validation.Dto.ValidationReqDto;
import bouzhar.quiz.demo.validation.Dto.ValidationResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/validation")
public class ValidationController {
    private final ValidationService validationService;

    @Autowired
    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping(path = "getValidations")
    public ResponseEntity<List<?>> getValidations() {
        return new ResponseEntity<>(validationService.getValidations(), HttpStatus.OK);
    }

    @DeleteMapping(path = "{questionId}/{answerId}")
    public ResponseEntity<ValidationResDto> deleteValidation(
            @PathVariable("questionId") Long questionId,
            @PathVariable("answerId") Long answerId) {
        return validationService.deleteValidation(questionId, answerId);
    }

    @PostMapping
    public ResponseEntity<?> addValidation(@RequestBody @Valid ValidationReqDto validationDto) {
        return validationService.addValidation(validationDto);
    }
    
}
