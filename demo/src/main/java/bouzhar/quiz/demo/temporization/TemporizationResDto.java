package bouzhar.quiz.demo.temporization;

import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import bouzhar.quiz.demo.question.dto.QuestionSimpleDto;
import bouzhar.quiz.demo.test.Test;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TemporizationResDto {

    //private Long id;
    private QuestionResDto question;
    private Integer duration;
}
