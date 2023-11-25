package bouzhar.quiz.demo.student.dto;

import bouzhar.quiz.demo.parentClasses.PersonDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class StudentDto extends PersonDto {
    private LocalDate registrationDate;
}
