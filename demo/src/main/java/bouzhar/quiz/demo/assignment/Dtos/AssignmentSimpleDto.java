package bouzhar.quiz.demo.assignment.Dtos;

import bouzhar.quiz.demo.question.dto.QuestionWithSimpleAnswers;
import bouzhar.quiz.demo.test.Dtos.TestResSimpleAttributesDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AssignmentSimpleDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private Integer chance;
    private boolean result;
    private String reason;
    private Float obtainedScore;

}
