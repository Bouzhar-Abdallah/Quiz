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

    @NotNull
    @Column(unique = true, nullable = false)
    @NonNull private String name;

    @ManyToOne()
    private Subject parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Subject> children;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Question> questions;
}
