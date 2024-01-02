package bouzhar.quiz.demo.question.dto;


import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.answer.dto.AnswerSimpleDto;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.enums.QuestionType;
import bouzhar.quiz.demo.validation.Dto.ValidationResQuestionDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class QuestionWithSimpleAnswers {
    public QuestionWithSimpleAnswers(QuestionSimpleDto question,List<AnswerSimpleDto> answers) {
        this.answers = answers;
        this.id = question.getId();
        this.text = question.getText();
    }

    private long id;
    private String text;
    private Float scorePoints;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    private List<AnswerSimpleDto> answers;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionWithSimpleAnswers that = (QuestionWithSimpleAnswers) o;
        return getId() == that.getId() && Objects.equals(getText(), that.getText()) && Objects.equals(getScorePoints(), that.getScorePoints()) && getType() == that.getType() && Objects.equals(getAnswers(), that.getAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getScorePoints(), getType(), getAnswers());
    }
}
