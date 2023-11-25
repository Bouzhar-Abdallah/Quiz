package bouzhar.quiz.demo.validation.Dto;

import bouzhar.quiz.demo.question.dto.QuestionSimpleDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationResNoAnswerDto {


    private QuestionSimpleDto question;
    private boolean isCorrect;
    private Float score;
}
