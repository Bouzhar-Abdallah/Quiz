package bouzhar.quiz.demo.validation.Dto;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.question.Question;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationResQuestionDto {
    private Answer answer;

    private boolean isCorrect;
    private Float score;
}
