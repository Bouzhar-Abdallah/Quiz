package bouzhar.quiz.demo.temporization;

import bouzhar.quiz.demo.question.dto.QuestionResDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TemporizationReqDto {

    private Long question_id;
    private Long test_id;
    private Integer duration;
}
