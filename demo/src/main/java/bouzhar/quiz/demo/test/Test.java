package bouzhar.quiz.demo.test;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "duration must not be null")
    private Integer duration;
    @NotNull(message = "successScore must not be null")
    private Float successScore;
    @NotNull(message = "showResults must not be null")
    private boolean showResults;
    @NotNull(message = "showResps must not be null")
    private boolean showResps;
    @NotNull(message = "maxAttempts must not be null")
    private Integer maxAttempts;
    @NotEmpty(message = "remarks cannot be empty")
    private String remarks;
    @NotEmpty(message = "instructions cannot be empty")
    private String instructions;

}
