package bouzhar.quiz.demo.assignment.Dtos;

import bouzhar.quiz.demo.response.dto.ResponseResDto;
import bouzhar.quiz.demo.test.Dtos.TestResSimpleAttributesDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AssignmentResDto {
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private Float obtainedScore;

    private TestResSimpleAttributesDto test;

    private List<ResponseResDto> responses;
}
