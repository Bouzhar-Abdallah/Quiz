package bouzhar.quiz.demo.Teacher;

import bouzhar.quiz.demo.parentClasses.PersonDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto extends PersonDto {
    private LocalDate regestrationDate;
    @NotEmpty(message = "speciality cannot be empty")
    private String speciality;
}
