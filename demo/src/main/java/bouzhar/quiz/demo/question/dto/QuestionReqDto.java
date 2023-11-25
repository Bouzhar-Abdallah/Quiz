package bouzhar.quiz.demo.question.dto;

import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.media.Media;
import bouzhar.quiz.demo.media.MediaDto;
import bouzhar.quiz.demo.question.enums.QuestionType;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.validation.Dto.ValidationResQuestionDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
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
public class QuestionReqDto {

    private long id;
    private Integer answersCount;
    private Integer correctAnswersCount;
    private String text;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    private List<Media> medias;
    private Long level_id;
    private Long subject_id;

}
