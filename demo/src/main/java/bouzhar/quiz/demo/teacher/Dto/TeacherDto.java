package bouzhar.quiz.demo.teacher.Dto;

import bouzhar.quiz.demo.parentClasses.PersonDto;
import bouzhar.quiz.demo.test.Dtos.TestResSimpleAttributesDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto extends PersonDto {
    private LocalDate regestrationDate;
    @NotEmpty(message = "speciality cannot be empty")
    private String speciality;
}
