package bouzhar.quiz.demo.validation;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.test.Test;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validation {
    @EmbeddedId
    @JsonIgnore
    private ValidationId validationId;

    @ManyToOne()
    @MapsId("questionId")
    @JoinColumn(name = "question_id")

    private Question question;

    @ManyToOne()
    @MapsId("answerId")
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Column(nullable = false)
    @Transient
    private Boolean isCorrect;

    @Column(nullable = false)
    @NotNull
    private Float score;

    public Boolean getIsCorrect(){
        return score > 0;
    }
}
