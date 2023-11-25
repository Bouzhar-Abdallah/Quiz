package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.assignment.Assignment;
import bouzhar.quiz.demo.parentClasses.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter

public final class Student extends Person {
    private LocalDate registrationDate;
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Assignment> assignments;
}
