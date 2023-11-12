package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.validation.Validation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    @NotNull(message = "Answer cannot be null")
    @Column(nullable = false, unique = true)
    @NonNull private String answer;

    @JsonIgnore
    @OneToMany(mappedBy = "answer")
    private List<Validation> validations;
}
