package bouzhar.quiz.demo.question.dto;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.answer.dto.AnswerWithValidation;
import bouzhar.quiz.demo.level.dtos.LevelSimpleDto;
import bouzhar.quiz.demo.media.MediaDto;
import bouzhar.quiz.demo.question.enums.QuestionType;
import bouzhar.quiz.demo.subject.dto.SubjectDto;
import bouzhar.quiz.demo.validation.Dto.ValidationResQuestionDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionWithValidations {
    private QuestionSimpleDto question;
    private List<AnswerWithValidation> answersWithValidation;

}
