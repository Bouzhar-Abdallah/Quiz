package bouzhar.quiz.demo.subject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubjectSimpleDto {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "SubjectSimpleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
