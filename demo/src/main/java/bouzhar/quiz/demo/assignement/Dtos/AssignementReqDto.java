package bouzhar.quiz.demo.assignement.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignementReqDto {
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private Float obtainedScore;
    @NotNull
    //@JsonProperty("test")

    private Long test_id;
    @NotNull
    //@JsonProperty("student")

    private Long student_id;
}
