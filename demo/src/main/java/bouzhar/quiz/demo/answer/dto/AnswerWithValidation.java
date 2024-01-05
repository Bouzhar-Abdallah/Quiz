package bouzhar.quiz.demo.answer.dto;

import bouzhar.quiz.demo.validation.Dto.ValidationResDto;
import lombok.Data;

@Data
public class AnswerWithValidation {
    private AnswerSimpleDto answer;
    private ValidationResDto validation;
    private Boolean isSelected;
}
