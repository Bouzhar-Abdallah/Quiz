package bouzhar.quiz.demo.teacher.Dto;

import bouzhar.quiz.demo.parentClasses.PersonDto;
import bouzhar.quiz.demo.test.Dtos.TestResSimpleAttributesDto;
import bouzhar.quiz.demo.test.Test;
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
public class TeacherResDto extends PersonDto {
    private LocalDate registrationDate;
    private String speciality;
    private List<TestResSimpleAttributesDto> tests;
}
