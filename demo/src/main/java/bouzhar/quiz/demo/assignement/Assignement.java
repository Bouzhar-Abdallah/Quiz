package bouzhar.quiz.demo.assignement;

import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.test.Test;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Assignement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull(message = "duration must not be null")
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private Float obtainedScore;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

}
