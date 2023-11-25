package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.question.dto.QuestionReqDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionServiceSpecification {
    QuestionResDto addQuestion(QuestionReqDto questionReqDto);
    QuestionResDto getQuestion(Long questionId);
    List<QuestionResDto> getQuestions();
    QuestionResDto updateQuestion(Long id, QuestionReqDto questionReqDto);
    QuestionResDto deleteQuestion(Long questionId);
}
