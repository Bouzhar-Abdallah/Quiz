package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.assignement.Assignement;
import bouzhar.quiz.demo.assignement.AssignementDto;
import bouzhar.quiz.demo.parentClasses.PersonDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class StudentDto extends PersonDto {
    private LocalDate regestrationDate;
}
