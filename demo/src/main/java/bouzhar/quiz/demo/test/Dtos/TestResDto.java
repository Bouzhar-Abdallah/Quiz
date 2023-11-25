package bouzhar.quiz.demo.test.Dtos;

import bouzhar.quiz.demo.assignment.Dtos.AssignmentResDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentSimpleDto;
import bouzhar.quiz.demo.teacher.Dto.TeacherDto;
import bouzhar.quiz.demo.temporization.Temporization;
import bouzhar.quiz.demo.temporization.TemporizationResDto;
import bouzhar.quiz.demo.validation.Dto.ValidationResDto;
import lombok.Data;

import java.util.List;

@Data

public class TestResDto {
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
    private TeacherDto teacher;
    private List<AssignmentSimpleDto> assignments;
}
