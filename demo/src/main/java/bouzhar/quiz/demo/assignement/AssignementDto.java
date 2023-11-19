package bouzhar.quiz.demo.assignement;

import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.student.StudentDto;
import bouzhar.quiz.demo.test.Test;
import bouzhar.quiz.demo.test.TestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignementDto {
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer chance;

    private boolean result;

    private Float obtainedScore;
    @NotNull
    //@JsonProperty("test")
    private TestDto test;
    @NotNull
    //@JsonProperty("student")
    private StudentDto student;
}
