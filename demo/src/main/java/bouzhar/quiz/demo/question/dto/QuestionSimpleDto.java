package bouzhar.quiz.demo.question.dto;


import bouzhar.quiz.demo.question.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionSimpleDto that = (QuestionSimpleDto) o;
        return getId() == that.getId() && Objects.equals(getAnswersCount(), that.getAnswersCount()) && Objects.equals(getCorrectAnswersCount(), that.getCorrectAnswersCount()) && Objects.equals(getText(), that.getText()) && Objects.equals(getScorePoints(), that.getScorePoints()) && getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnswersCount(), getCorrectAnswersCount(), getText(), getScorePoints(), getType());
    }
}
