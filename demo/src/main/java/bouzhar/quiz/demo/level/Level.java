package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.question.Question;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Level {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    @NonNull private String description;
    @Column(nullable = false)
    @NonNull private Float maxPoints;
    @Column(nullable = false)
    @NonNull private Float minPoints;


    @JsonIgnore
    @OneToMany(mappedBy = "level",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Question> questions;
}
