package bouzhar.quiz.demo.assignment;

import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.test.Test;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "start date must not be null")
    private LocalDateTime startDate;

    @NotNull(message = "end date must not be null")
    private LocalDateTime endDate;

    private String reason;

    @Min(value = 1, message = "chance must be greater than 0")
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
