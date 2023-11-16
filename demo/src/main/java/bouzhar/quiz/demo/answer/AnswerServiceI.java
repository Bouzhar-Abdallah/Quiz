package bouzhar.quiz.demo.answer;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnswerServiceI {
    /*
     *
     * Methods
     *
     * */
    ResponseEntity<List<AnswerDto>> getAnswers();

    boolean existsAnswerByAnswer(String answer);

    ResponseEntity<AnswerDto> addAnswer(AnswerDto answerDto);


    ResponseEntity<AnswerDto> updateAnswer(AnswerDto answerDto);

    ResponseEntity<String> deleteAnswer(Long answerId);

    ResponseEntity<AnswerDto> getAnswer(Long answerId);
}
