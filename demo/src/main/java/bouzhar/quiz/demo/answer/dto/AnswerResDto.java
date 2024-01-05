package bouzhar.quiz.demo.answer.dto;

import bouzhar.quiz.demo.validation.Dto.ValidationResNoAnswerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class AnswerResDto {
    private Long id;
    @NotBlank(message = "Answer cannot be empty")
    private String answer;
    private List<ValidationResNoAnswerDto> validations;
    private Integer uses;

}
