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
    @Column(nullable = false, unique = true)
    @NonNull private String answer;

    @JsonIgnore
    @OneToMany(mappedBy = "answer",cascade = CascadeType.REMOVE)
    private List<Validation> validations;
}
