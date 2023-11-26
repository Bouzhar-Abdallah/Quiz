package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.question.Question;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity

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
    @NonNull
    @NotBlank(message = "Level description is mandatory")
    private String description;

    @Column(nullable = false)
    @NonNull
    @NotNull(message = "maxPoints can't be null")
    @Min(value = 0,message = "maxPoints can't be negative")
    private Float maxPoints;

    @Column(nullable = false)
    @NonNull
    @NotNull(message = "minPoints can't be null")
    @Min(value = 0,message = "minPoints can't be negative")
    private Float minPoints;

    @OneToMany(mappedBy = "level",fetch = FetchType.LAZY)
    private List<Question> questions;
}
