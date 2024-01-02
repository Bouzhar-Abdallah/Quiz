package bouzhar.quiz.demo.question.dto;


import bouzhar.quiz.demo.media.MediaDto;
import bouzhar.quiz.demo.question.enums.QuestionType;
import bouzhar.quiz.demo.validation.Dto.ValidationAnswerNoScore;
import bouzhar.quiz.demo.validation.Dto.ValidationResQuestionDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuestionWithAnswersDto {
    private long id;
    private Integer answersCount;
    private Integer correctAnswersCount;
    private String text;
    private Float scorePoints;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    private List<ValidationAnswerNoScore> validations;
    private List<MediaDto> medias;
}
