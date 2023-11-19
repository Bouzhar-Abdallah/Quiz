package bouzhar.quiz.demo.teacher;

import bouzhar.quiz.demo.parentClasses.Person;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Person {
    private LocalDate regestrationDate;
    private String speciality;
}
