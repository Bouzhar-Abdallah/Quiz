package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.level.dtos.LevelDto;
import bouzhar.quiz.demo.question.Question;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table
@Data

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

    public LevelDto toLevelDto() {
        return new LevelDto(this.id,this.description,this.maxPoints,this.minPoints);
    }


/*    @JsonIgnore
    @OneToMany(mappedBy = "level",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Question> questions;*/
}