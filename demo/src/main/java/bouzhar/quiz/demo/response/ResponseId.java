package bouzhar.quiz.demo.response;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class ResponseId implements Serializable{

    private Long assignmentId;
    private Long questionId;
    private Long answerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseId that = (ResponseId) o;
        return Objects.equals(assignmentId, that.assignmentId) &&
                Objects.equals(questionId, that.questionId) &&
                Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, questionId, answerId);
    }
}