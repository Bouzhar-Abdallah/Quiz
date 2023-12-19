package bouzhar.quiz.demo.student.dto;

import bouzhar.quiz.demo.assignment.Dtos.AssignmentResDto;
import bouzhar.quiz.demo.parentClasses.PersonDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class StudentSimpleDto extends PersonDto {
    private LocalDate registrationDate;
}
