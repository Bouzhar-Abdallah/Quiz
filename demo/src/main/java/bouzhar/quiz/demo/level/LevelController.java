package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.level.dtos.LevelDto;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.QuestionDto;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping(path = "api/v1/level")
public class LevelController {
    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping(path = "getLevels")
    public ResponseEntity<List<LevelDto>> getLevels() {
        return levelService.getLevels();
    }

    @GetMapping(path = "getLevel/{levelId}")
    public ResponseEntity<?> getLevel(@PathVariable("levelId") Long levelId) {
        return levelService.getLevel(levelId);
    }
    @GetMapping(path = "{levelId}/questions")
    public ResponseEntity<List<Question>> getLevelQuestions(@PathVariable("levelId") Long levelId) {
        return levelService.getLevelQuestions(levelId);
    }

    @PostMapping(path = "addLevel"/*,consumes = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<LevelDto> addLevel(@RequestBody @Valid LevelDto level) {
        return levelService.addNewLevel(level);
    }

    @PutMapping()
    public ResponseEntity<LevelDto> updateLevel(
            @RequestBody @Valid LevelDto levelDto
    ) {
        return levelService.updateLevel(levelDto.getId(), levelDto);
        //return ResponseEntity.ok(levelService.findById(levelDto.getId()));
    }

    @DeleteMapping(path = "{levelId}")
    public void deleteLevel(@PathVariable("levelId") Long levelId) {
        levelService.deleteLevel(levelId);
    }
}
