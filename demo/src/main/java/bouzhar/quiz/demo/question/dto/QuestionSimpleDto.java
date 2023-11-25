package bouzhar.quiz.demo.question.dto;


import bouzhar.quiz.demo.question.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
public class QuestionSimpleDto {
    private long id;
    private Integer answersCount;
    private Integer correctAnswersCount;
    private String text;
    private Float scorePoints;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
}
