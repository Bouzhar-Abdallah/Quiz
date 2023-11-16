package bouzhar.quiz.demo.subject;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
public class SubjectDto {

    private Long id;

    private String name;

    private Subject parent;
}
