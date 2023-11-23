package bouzhar.quiz.demo.test.Dtos;

import lombok.Data;

@Data
public class TestResSimpleAttributesDto {
    private Long id;

    private String title;
    private Integer duration;
    private Float successScore;
    private boolean showResults;
    private boolean showResps;
    private Integer maxAttempts;
    private String remarks;
    private String instructions;

}
