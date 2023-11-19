package bouzhar.quiz.demo.parentClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String adress;
}
