package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.assignement.Assignement;
import bouzhar.quiz.demo.parentClasses.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "student")
    private List<Assignement> assignements;
}
