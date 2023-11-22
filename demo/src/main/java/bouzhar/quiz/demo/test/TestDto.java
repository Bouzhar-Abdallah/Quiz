package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.assignement.Assignement;
import bouzhar.quiz.demo.assignement.AssignementDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

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
    private List<AssignementDto> assignementDtos;
}
