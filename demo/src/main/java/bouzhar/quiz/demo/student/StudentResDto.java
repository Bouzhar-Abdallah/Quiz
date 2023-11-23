package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.assignement.Dtos.AssignementResDto;
import bouzhar.quiz.demo.parentClasses.PersonDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class StudentResDto extends PersonDto {
    private LocalDate regestrationDate;
    private List<AssignementResDto> assignements;
}
