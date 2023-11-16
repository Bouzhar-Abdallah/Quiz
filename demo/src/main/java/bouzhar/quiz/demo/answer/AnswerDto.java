package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.validation.Validation;
import lombok.Data;

import java.util.List;

@Data
public class AnswerDto {
    private long id;

    private String answer;

    private List<Validation> validations;
}
