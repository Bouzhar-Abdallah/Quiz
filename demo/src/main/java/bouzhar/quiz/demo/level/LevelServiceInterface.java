package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.level.dtos.LevelResDto;
import bouzhar.quiz.demo.level.dtos.LevelSimpleDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LevelServiceInterface {
    LevelSimpleDto addNewLevel(LevelSimpleDto levelSimpleDto);

    LevelResDto getLevel(Long levelId);
    List<LevelResDto> getLevels();
    LevelSimpleDto updateLevel(Long id, LevelSimpleDto levelSimpleDto);

    LevelSimpleDto deleteLevel(Long levelId);
    List<QuestionResDto> getLevelQuestions(Long levelId);
    boolean existsLevelByText(String description);
}
