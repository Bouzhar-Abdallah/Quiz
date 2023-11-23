package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.assignement.Assignement;
import bouzhar.quiz.demo.parentClasses.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter

public final class Student extends Person {
    private LocalDate regestrationDate;
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Assignement> assignements;
}
