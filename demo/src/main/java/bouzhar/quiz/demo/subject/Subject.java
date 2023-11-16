package bouzhar.quiz.demo.subject;

import bouzhar.quiz.demo.question.Question;
import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class Subject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @NotNull
    @NonNull private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Subject parent;
    @JsonIgnore
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Question> questionList;
}
