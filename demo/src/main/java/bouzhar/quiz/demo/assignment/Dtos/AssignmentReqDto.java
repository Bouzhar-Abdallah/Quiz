package bouzhar.quiz.demo.assignment.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentReqDto {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private String reason;

    private Float obtainedScore;

    @NotNull
    private Long test_id;

    @NotNull
    private Long student_id;
}
