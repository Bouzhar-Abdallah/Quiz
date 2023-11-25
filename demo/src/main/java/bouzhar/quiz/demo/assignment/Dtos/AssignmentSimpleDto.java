package bouzhar.quiz.demo.assignment.Dtos;

import bouzhar.quiz.demo.test.Dtos.TestResSimpleAttributesDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentSimpleDto {
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private Float obtainedScore;

}
