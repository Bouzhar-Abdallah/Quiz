package bouzhar.quiz.demo.response.dto;

import bouzhar.quiz.demo.assignment.Assignment;
import bouzhar.quiz.demo.validation.Validation;
import lombok.Data;

@Data
public class ResponseReqDto {
    private Long id;

    private Long assignment_id;
    private Long answer_id;
    private Long question_id;
}
