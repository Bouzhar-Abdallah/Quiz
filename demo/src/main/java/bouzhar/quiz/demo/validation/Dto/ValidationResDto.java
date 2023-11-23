package bouzhar.quiz.demo.validation.Dto;

import bouzhar.quiz.demo.answer.AnswerDto;
import bouzhar.quiz.demo.question.dto.QuestionSimpleDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationResDto {


    private QuestionSimpleDto question;
    private AnswerDto answer;
    private boolean isCorrect;
    private Float score;
}
