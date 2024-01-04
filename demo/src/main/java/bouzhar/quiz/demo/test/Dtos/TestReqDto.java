package bouzhar.quiz.demo.test.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class TestReqDto {
    private Long id;

    private String title;
    private Integer duration;
    private Float successScore;
    private boolean showResults;
    private boolean showResps;
    private Integer maxAttempts;
    private String remarks;
    private String instructions;
    //@NotNull(message = "teacher id cannot be null")
    private Long teacher_id;
}
