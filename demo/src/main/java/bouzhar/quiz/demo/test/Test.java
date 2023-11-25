package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.assignment.Assignment;
import bouzhar.quiz.demo.teacher.Teacher;
import bouzhar.quiz.demo.temporization.Temporization;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "title cannot be empty")
    private String title;

    @NotNull(message = "duration must not be null")
    @Min(value = 0,message = "duration cannot be 0")
    private Integer duration;

    @NotNull(message = "successScore must not be null")
    @Min(value = 0,message = "success score cannot be 0")
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

    @OneToMany(mappedBy = "test",fetch = FetchType.LAZY)
    private List<Assignment> assignments;

    @ManyToOne()
    private Teacher teacher;

    @OneToMany(mappedBy = "test",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Temporization> temporizations;
}

