package bouzhar.quiz.demo.validation;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.question.Question;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Validation {
    @EmbeddedId
    @JsonIgnore
    private ValidationId validationId;

    @ManyToOne()
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @ManyToOne()
    @MapsId("answerId")
    @JoinColumn(name = "answer_id")
    private Answer answer;

    private boolean isCorrect;

}
