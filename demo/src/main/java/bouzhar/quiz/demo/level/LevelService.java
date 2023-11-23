package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.exception.ValidationException;
import bouzhar.quiz.demo.level.dtos.LevelDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LevelService implements LevelServiceInterface {

    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LevelService(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<LevelDto>> getLevels() {
        return ResponseEntity.ok(levelRepository.findAll().stream()
                .map(level -> modelMapper.map(level, LevelDto.class))
                .toList());
    }

    @Override
    public ResponseEntity<LevelDto> addNewLevel(LevelDto levelDto) throws ValidationException {

        if (existsLevelByText(levelDto.getDescription())) {
            throw new ValidationException("Level with the same text already exists");
        }

        Level createdLevel = levelRepository.save(modelMapper.map(levelDto, Level.class));
        LevelDto createdLevelDto = modelMapper.map(createdLevel, LevelDto.class);
        return new ResponseEntity<>(createdLevelDto, HttpStatus.CREATED);
    }

    @Override
    public boolean existsLevelByText(String description) {
        return levelRepository.existsByDescription(description);
    }


    @Transactional
    @Override
    public ResponseEntity<LevelDto> updateLevel(Long id, LevelDto levelDto) {
        Level existingLevel = levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + id + " does not exist"));

        existingLevel.setDescription(levelDto.getDescription());
        existingLevel.setMaxPoints(levelDto.getMaxPoints());
        existingLevel.setMinPoints(levelDto.getMinPoints());
        Level updatedLevel = levelRepository.save(existingLevel);
        LevelDto updatedLevelDto = modelMapper.map(updatedLevel, LevelDto.class);
        return ResponseEntity.ok(updatedLevelDto);}

    @Override
    public ResponseEntity<String> deleteLevel(Long levelId) {
        Level existingLevel = levelRepository.findById(levelId)
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + levelId + " does not exist"));
        levelRepository.delete(existingLevel);
        return ResponseEntity.ok("Level deleted");}

    @Override
    public ResponseEntity<LevelDto> getLevel(Long levelId) throws ResourceNotFoundException {
        return levelRepository.findById(levelId)
                .map(level -> ResponseEntity.ok(modelMapper.map(level, LevelDto.class)))
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + levelId + " does not exist"));}
    @Override
    public ResponseEntity<List<QuestionResDto>> getLevelQuestions(Long levelId) throws ResourceNotFoundException{
        Level level = levelRepository.findById(levelId).orElseThrow(() -> new ResourceNotFoundException("The level with ID " + levelId + " does not exist"));
        return ResponseEntity.ok(level.getQuestions().stream()
                .map(question -> modelMapper.map(question, QuestionResDto.class))
                .toList());}}