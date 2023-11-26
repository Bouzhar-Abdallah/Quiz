package bouzhar.quiz.demo.response.dto;

import bouzhar.quiz.demo.assignment.Dtos.AssignmentResDto;
import bouzhar.quiz.demo.validation.Dto.ValidationResDto;
import bouzhar.quiz.demo.validation.Validation;
import lombok.Data;

@Data
public class ResponseResDto {
    private Long id;

    private AssignmentResDto assignment;

    private ValidationResDto validation;
}
