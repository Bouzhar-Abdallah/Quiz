package bouzhar.quiz.demo.level.dtos;

import bouzhar.quiz.demo.level.Level;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class LevelDto {
    private long id;

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotNull(message = "maxPoints can't be null")
    @Min(value = 0,message = "maxPoints can't be null")
    private Float maxPoints;

    @NotNull(message = "minPoints can't be null")
    @Min(value = 0,message = "Points can't be negative")
    private Float minPoints;

    public Level toLevel(){
        return new Level(this.description,this.maxPoints,this.minPoints);
    }
}
