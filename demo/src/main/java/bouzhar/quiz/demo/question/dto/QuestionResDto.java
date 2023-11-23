package bouzhar.quiz.demo.question.dto;

import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.level.dtos.LevelDto;
import bouzhar.quiz.demo.media.MediaDto;
import bouzhar.quiz.demo.question.enums.QuestionType;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.subject.SubjectDto;
import bouzhar.quiz.demo.temporization.Temporization;
import bouzhar.quiz.demo.temporization.TemporizationResDto;
import bouzhar.quiz.demo.validation.Dto.ValidationResQuestionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResDto {

    private long id;
    private Integer answersCount;
    private Integer correctAnsqersCount;
    @NotBlank(message = "question text can't be null")
    private String text;

    private BigInteger duration;
    private Float scorePoints;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    private List<ValidationResQuestionDto> validations;
    private List<MediaDto> medias;
    private LevelDto level;
    private SubjectDto subject;

}
