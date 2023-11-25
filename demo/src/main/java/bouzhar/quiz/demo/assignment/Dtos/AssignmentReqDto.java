package bouzhar.quiz.demo.assignment.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentReqDto {
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private Float obtainedScore;

    @NotNull
    private Long test_id;

    @NotNull
    private Long student_id;
}
