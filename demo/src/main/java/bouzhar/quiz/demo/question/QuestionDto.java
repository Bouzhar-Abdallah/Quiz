package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.validation.Validation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
public class QuestionDto {

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


    private List<Validation> validations;

    @NotNull(message = "level is required")
    private Level level;
    @NotNull(message = "subject is required")
    private Subject subject;

    public Long getLevelId() {
        return level.getId();
    }

    public Long getSubjectId() {
        return subject.getId();
    }
}
