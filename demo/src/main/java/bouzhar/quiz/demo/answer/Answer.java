package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.validation.Validation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Answer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(nullable = false, unique = true)
    @NonNull
    @NotBlank(message = "Answer cannot be empty")
    private String answer;

    @OneToMany(mappedBy = "answer",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Validation> validations;

    @Transient
    private Integer uses;
    public Integer getUses() {
        return validations != null ? validations.size() : 0;
    }
}
