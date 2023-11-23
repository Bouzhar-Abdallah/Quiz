package bouzhar.quiz.demo.question.dto;

import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.media.Media;
import bouzhar.quiz.demo.media.MediaDto;
import bouzhar.quiz.demo.question.enums.QuestionType;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.validation.Dto.ValidationResQuestionDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @NotNull(message ="Id can't be null")
    private long id;
    @NotNull(message ="Answer count can't be 0" )
    private Integer answersCount;
    @NotNull(message = "correct answer count can't be 0")
    private Integer correctAnsqersCount;
    @NotNull(message = "text can't be null")
    @NotBlank(message = "question text can't be null")
    private String text;
    @NotNull(message = "duration can't be null")

    private BigInteger duration;
    @NotNull(message = "Id can't be null")
    private Float scorePoints;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Id can't be null")
    private QuestionType type;



    private List<Media> medias;
    @NotNull(message = "level is required")
    private Long level_id;
    @NotNull(message = "subject is required")
    private Long subject_id;

}
