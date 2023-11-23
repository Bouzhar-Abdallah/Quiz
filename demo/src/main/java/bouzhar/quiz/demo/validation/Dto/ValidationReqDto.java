package bouzhar.quiz.demo.validation.Dto;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.question.Question;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationReqDto {

    @NotNull
    private Long question_id;
    @NotNull
    private Long answer_id;
    @NotNull
    private Boolean isCorrect;
    @NotNull
    private Float score;
}
