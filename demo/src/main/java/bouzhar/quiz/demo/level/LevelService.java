package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.exception.CustomValidationException;
import bouzhar.quiz.demo.level.dtos.LevelSimpleDto;
import bouzhar.quiz.demo.level.dtos.LevelResDto;
import bouzhar.quiz.demo.level.exceptions.LevelPointsException;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class LevelService implements LevelServiceInterface {

    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;


    /*
     *
     * methods
     *
     * */

    // add new level
    @Override
    public LevelSimpleDto addNewLevel(LevelSimpleDto levelSimpleDto) throws CustomValidationException {

        if (existsLevelByText(levelSimpleDto.getDescription())) {
            throw new CustomValidationException("Level with the same text already exists");
        }
        if (levelSimpleDto.getMinPoints() > levelSimpleDto.getMaxPoints()) {
            throw new LevelPointsException("Min points can't be greater than max points");
        }

        Level createdLevel = levelRepository.save(modelMapper.map(levelSimpleDto, Level.class));
        return modelMapper.map(createdLevel, LevelSimpleDto.class);

    }

    // get level by id
    @Override
    public LevelResDto getLevel(Long levelId) throws ResourceNotFoundException {
        return levelRepository.findById(levelId)
                .map(level -> modelMapper.map(level, LevelResDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + levelId + " does not exist"));
    }

    // get all levels
    @Override
    public List<LevelResDto> getLevels() {
        return levelRepository.findAll().stream()
                .map(level -> modelMapper.map(level, LevelResDto.class))
                .toList();
    }

    // update level
    @Override
    public LevelSimpleDto updateLevel(Long id, LevelSimpleDto levelSimpleDto) {
        Level existingLevel = levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + id + " does not exist"));

        if (levelSimpleDto.getMinPoints() > levelSimpleDto.getMaxPoints()) {
            throw new LevelPointsException("Min points can't be greater than max points");
        }
        existingLevel.setDescription(levelSimpleDto.getDescription());
        existingLevel.setMaxPoints(levelSimpleDto.getMaxPoints());
        existingLevel.setMinPoints(levelSimpleDto.getMinPoints());

        Level updatedLevel = levelRepository.save(existingLevel);

        return modelMapper.map(updatedLevel, LevelSimpleDto.class);

    }

    // delete level
    @Override
    public LevelSimpleDto deleteLevel(Long levelId) {
        Level existingLevel = levelRepository.findById(levelId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The level with ID " + levelId + " does not exist"
                ));
            if (!existingLevel.getQuestions().isEmpty()) {
                throw new IllegalStateException("The level with ID " + levelId + " has questions, deleting it would cause data lose");
            } else {
                levelRepository.delete(existingLevel);
            }
        return modelMapper.map(existingLevel, LevelSimpleDto.class);
    }

    // get a level questions
    @Override
    public List<QuestionResDto> getLevelQuestions(Long levelId) throws ResourceNotFoundException {
        Level level = levelRepository.findById(levelId).orElseThrow(() -> new ResourceNotFoundException("The level with ID " + levelId + " does not exist"));
        return level.getQuestions().stream()
                .map(question -> modelMapper.map(question, QuestionResDto.class))
                .toList();
    }

    /*
     *
     * helpers
     *
     * */
    @Override
    public boolean existsLevelByText(String description) {
        return levelRepository.existsByDescription(description);
    }

    @Override
    public Page<LevelResDto> getPaginatedAnswers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return levelRepository.findAll(pageRequest).map(level -> modelMapper.map(level, LevelResDto.class));
    }
}