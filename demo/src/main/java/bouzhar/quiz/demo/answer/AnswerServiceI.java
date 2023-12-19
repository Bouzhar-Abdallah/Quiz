package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.answer.dto.AnswerSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnswerServiceI {
    AnswerSimpleDto addAnswer(AnswerSimpleDto answerSimpleDto);
    AnswerResDto getAnswer(Long answerId);
    List<AnswerResDto> getAnswers();
    AnswerResDto updateAnswer(AnswerSimpleDto answerSimpleDto);
    AnswerResDto deleteAnswer(Long answerId);
    boolean existsAnswerByAnswer(String answer);

    Page<AnswerResDto> getPaginatedAnswers(int page, int size);
}
