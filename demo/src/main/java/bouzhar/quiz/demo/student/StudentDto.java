package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.parentClasses.PersonDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class StudentDto extends PersonDto {
    private LocalDate regestrationDate;
}
