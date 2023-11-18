package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.parentClasses.Person;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter

public final class Student extends Person {
    private LocalDate regestrationDate;
}
