package bouzhar.quiz.demo.response.dto;

import bouzhar.quiz.demo.assignment.Assignment;
import bouzhar.quiz.demo.validation.Validation;
import lombok.Data;

import java.util.List;

@Data
public class ResponseReqDto {
    private Long assignment_id;
    private List<Long> answer_ids;
    private Long question_id;
}
