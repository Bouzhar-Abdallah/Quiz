package bouzhar.quiz.demo.assignment;

import bouzhar.quiz.demo.response.Response;
import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.test.Test;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "start date must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @NotNull(message = "end date must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private String reason;

    @Min(value = 1, message = "chance must be greater than 0")
    private Integer chance;

    private boolean result;
    @Column(columnDefinition = "default false")
    private Boolean isPassed;

    private Float obtainedScore;
    @ManyToOne()

    private Test test;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "assignment")
    private List<Response> responses;

     public Integer getAnsweredQuestionsCount(){
        return this.responses.stream()
                .map(response -> response.getValidation().getQuestion())
                .collect(Collectors.toSet())
                .size();
     }

}
