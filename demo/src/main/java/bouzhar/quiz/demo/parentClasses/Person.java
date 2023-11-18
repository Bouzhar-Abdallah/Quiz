package bouzhar.quiz.demo.parentClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Setter
@Getter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "first name cannot be empty")
    @Column(nullable = false)
    private String firstName;
    @NotEmpty(message = "last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email not valid")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "adresse cannot be empty")
    private String adress;
}
