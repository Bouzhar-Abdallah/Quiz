package bouzhar.quiz.demo.temporization;

import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.test.Test;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Temporization {
    public Temporization(TemporizationId temporizationId, Integer duration) {
        this.temporizationId = temporizationId;
        this.duration = duration;
    }

    @EmbeddedId
    @JsonIgnore
    private TemporizationId temporizationId;

    @MapsId("questionId")
    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @MapsId("testId")
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    private Integer duration;
}
