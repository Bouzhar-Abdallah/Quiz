package bouzhar.quiz.demo.temporization;

import bouzhar.quiz.demo.validation.ValidationId;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporizationId implements Serializable {
    private Long questionId;
    private Long testId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemporizationId that = (TemporizationId) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, testId);
    }
}
