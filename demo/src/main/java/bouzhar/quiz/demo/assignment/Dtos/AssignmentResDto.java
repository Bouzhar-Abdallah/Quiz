package bouzhar.quiz.demo.assignment.Dtos;

import bouzhar.quiz.demo.question.dto.QuestionWithSimpleAnswers;
import bouzhar.quiz.demo.response.dto.ResponseResDto;
import bouzhar.quiz.demo.student.dto.StudentResDto;
import bouzhar.quiz.demo.student.dto.StudentSimpleDto;
import bouzhar.quiz.demo.test.Dtos.TestResSimpleAttributesDto;
import bouzhar.quiz.demo.test.Dtos.TestResSimpleWithQuestionsDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AssignmentResDto {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private String reason;

    private Float obtainedScore;

    private TestResSimpleWithQuestionsDto test;
    private StudentSimpleDto student;
    private List<ResponseResDto> responses;
    private Boolean isPassed;
}
