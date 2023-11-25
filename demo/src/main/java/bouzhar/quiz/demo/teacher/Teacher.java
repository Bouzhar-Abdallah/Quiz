package bouzhar.quiz.demo.teacher;

import bouzhar.quiz.demo.parentClasses.Person;
import bouzhar.quiz.demo.test.Test;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Person {
    private LocalDate registrationDate;
    private String speciality;
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Test> tests;
}
