package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.level.dtos.LevelDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(path = "api/v2/level")
public class LevelController {
    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping()
    public ResponseEntity<List<LevelDto>> getLevels() {
        return levelService.getLevels();
    }

    @GetMapping(path = "{levelId}")
    public ResponseEntity<LevelDto> getLevel(@PathVariable("levelId") Long levelId) {
        return levelService.getLevel(levelId);
    }
    @GetMapping(path = "{levelId}/questions")
    public ResponseEntity<List<QuestionResDto>> getLevelQuestions(@PathVariable("levelId") Long levelId) {
        return levelService.getLevelQuestions(levelId);
    }

    @PostMapping()
    public ResponseEntity<LevelDto> addLevel(@RequestBody @Valid LevelDto level) {
        return levelService.addNewLevel(level);
    }

    @PutMapping()
    public ResponseEntity<LevelDto> updateLevel(@RequestBody @Valid LevelDto levelDto) {
        return levelService.updateLevel(levelDto.getId(), levelDto);

    }

    @DeleteMapping(path = "{levelId}")
    public ResponseEntity<String> deleteLevel(@PathVariable("levelId") Long levelId) {
        return levelService.deleteLevel(levelId);
    }
}
