package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.validation.Validation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @SequenceGenerator(
            name ="answer_sequence",
            sequenceName ="answer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_sequence"
    )
    private long id;
    @NonNull private String answer;

    @JsonIgnore
    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Validation> validations;
}
