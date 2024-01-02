package bouzhar.quiz.demo.test.Dtos;

import bouzhar.quiz.demo.temporization.TemporizationResDto;
import lombok.Data;

import java.util.List;

@Data
public class TestResSimpleWithQuestionsDto {
    private Long id;

    private String title;
    private Integer duration;
    private Float successScore;
    private boolean showResults;
    private boolean showResps;
    private Integer maxAttempts;
    private String remarks;
    private String instructions;
    private List<TemporizationResDto> temporizations;
}
