package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.validation.Validation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AnswerDto {
    private long id;
    @NotEmpty(message = "Answer cannot be null")
    private String answer;

    //private List<Validation> validations;
}
