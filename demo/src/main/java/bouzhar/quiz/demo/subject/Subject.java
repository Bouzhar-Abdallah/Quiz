package bouzhar.quiz.demo.subject;

import bouzhar.quiz.demo.question.Question;
import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class Subject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String name;

    @ManyToOne()
    private Subject parent;
/*
    @JsonIgnore
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Question> questionList;*/
}
