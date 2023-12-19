package bouzhar.quiz.demo.assignment.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AssignmentListReqDto {
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private String reason;

    private Float obtainedScore;

    @NotNull
    private Long test_id;

    @NotNull
    private List<Long> student_ids;
}
