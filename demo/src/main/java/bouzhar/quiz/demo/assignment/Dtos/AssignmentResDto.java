package bouzhar.quiz.demo.assignment.Dtos;

import bouzhar.quiz.demo.response.dto.ResponseResDto;
import bouzhar.quiz.demo.student.dto.StudentResDto;
import bouzhar.quiz.demo.student.dto.StudentSimpleDto;
import bouzhar.quiz.demo.test.Dtos.TestResSimpleAttributesDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AssignmentResDto {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private String reason;

    private Float obtainedScore;

    private TestResSimpleAttributesDto test;
    private StudentSimpleDto student;

    private List<ResponseResDto> responses;
}
