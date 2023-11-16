package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.level.dtos.LevelDto;
import bouzhar.quiz.demo.question.QuestionDto;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LevelServiceInterface {
    ResponseEntity<LevelDto> addNewLevel(LevelDto levelDto);

    boolean existsLevelByText(String description);

    @Transactional
    ResponseEntity<LevelDto> updateLevel(Long id, LevelDto levelDto);

    ResponseEntity<String> deleteLevel(Long levelId);

    ResponseEntity<LevelDto> getLevel(Long levelId);

    ResponseEntity<List<QuestionDto>> getLevelQuestions(Long levelId);
}
