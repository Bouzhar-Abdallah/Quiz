package bouzhar.quiz.demo.subject.dto;

import bouzhar.quiz.demo.subject.Subject;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
public class SubjectDto {

    private Long id;
    private String name;
    private SubjectSimpleDto parent;
    private List<SubjectSimpleDto> children;
}
