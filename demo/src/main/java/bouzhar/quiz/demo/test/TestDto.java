package bouzhar.quiz.demo.test;

import lombok.Data;

@Data
public class TestDto {
    private Long id;

    private Integer duration;
    private Float successScore;
    private boolean showResults;
    private boolean showResps;
    private Integer maxAttempts;
    private String remarks;
    private String instructions;
}
