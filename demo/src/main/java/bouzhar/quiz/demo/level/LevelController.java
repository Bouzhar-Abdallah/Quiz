package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.level.dtos.LevelSimpleDto;
import bouzhar.quiz.demo.level.dtos.LevelResDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /*
     *
     * mehotds
     *
     * */

    // add new level
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LevelSimpleDto addLevel(@RequestBody @Valid LevelSimpleDto level) {
        return levelService.addNewLevel(level);
    }

    // get level by id
    @GetMapping(path = "{levelId}")
    @ResponseStatus(HttpStatus.OK)
    public LevelResDto getLevel(@PathVariable("levelId") Long levelId) {
        return levelService.getLevel(levelId);
    }

    // get all levels
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<LevelResDto> getLevels() {
        return levelService.getLevels();
    }

    // update level
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public LevelSimpleDto updateLevel(@RequestBody @Valid LevelSimpleDto levelSimpleDto) {
        return levelService.updateLevel(levelSimpleDto.getId(), levelSimpleDto);
    }

    // delete level
    @DeleteMapping(path = "{levelId}")
    @ResponseStatus(HttpStatus.OK)
    public LevelSimpleDto deleteLevel(@PathVariable("levelId") Long levelId) {
        return levelService.deleteLevel(levelId);
    }

    // get a level questions
    @GetMapping(path = "{levelId}/questions")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionResDto> getLevelQuestions(@PathVariable("levelId") Long levelId) {
        return levelService.getLevelQuestions(levelId);
    }
}
