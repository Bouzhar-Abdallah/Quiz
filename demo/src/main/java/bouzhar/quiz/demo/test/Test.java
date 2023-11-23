package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.assignement.Assignement;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.teacher.Teacher;
import bouzhar.quiz.demo.temporization.Temporization;
import bouzhar.quiz.demo.validation.Validation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDate;
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
    @OneToMany(mappedBy = "test",fetch = FetchType.LAZY)
    private List<Assignement> assignements;
    @ManyToOne()
    private Teacher teacher;
    @OneToMany(mappedBy = "test",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Temporization> temporizations;
}
