package bouzhar.quiz.demo.validation;

import bouzhar.quiz.demo.validation.Dto.ValidationReqDto;
import bouzhar.quiz.demo.validation.Dto.ValidationResDto;

import java.util.List;

public interface ValidationServiceSpecification {
    // add validation
    ValidationResDto addValidation(ValidationReqDto validationDto);

    // get all validations
    List<ValidationResDto> getValidations();

    // delete validation
    ValidationResDto deleteValidation(Long questionId, Long answerId);
}
