package bouzhar.quiz.demo.answer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnswerSimpleDto {
    private Long id;
    @NotBlank(message = "Answer cannot be empty")
    private String answer;
    private Integer uses;
}
