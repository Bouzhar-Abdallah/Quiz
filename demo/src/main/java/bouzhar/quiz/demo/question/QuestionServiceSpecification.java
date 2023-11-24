package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.question.dto.QuestionReqDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionServiceSpecification {
    List<QuestionResDto> getQuestions();

    ResponseEntity<QuestionResDto> addQuestion(QuestionReqDto questionReqDto);

    ResponseEntity<QuestionResDto> updateQuestion(Long id, QuestionReqDto questionReqDto);

    ResponseEntity<QuestionResDto> findById(Long questionId);

    ResponseEntity<QuestionResDto> deleteQuestion(Long questionId);
}
