package bouzhar.quiz.demo.assignement.Dtos;

import bouzhar.quiz.demo.test.Dtos.TestResSimpleAttributesDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignementResDto {
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private Float obtainedScore;

    private TestResSimpleAttributesDto test;

    /*@NotNull
    //@JsonProperty("test")
    private TestDto test;*/

    //private List<StudentDto> students;
}
