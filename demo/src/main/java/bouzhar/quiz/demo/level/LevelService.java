package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.level.dtos.LevelDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LevelService {
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<Level> getLevels() {
        return levelRepository.findAll();
    }

    public ResponseEntity<LevelDto> addNewLevel(LevelDto levelDto) {
        // Check if a level with the same text already exists
        if (existsLevelByText(levelDto.getDescription())) {
            throw new ValidationException("Level with the same text already exists");
        }

        try {
            Level createdLevel = levelRepository.save(levelDto.toLevel());
            return new ResponseEntity<>(createdLevel.toLevelDto(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidationException("Failed to create the level");
        }
    }


    public boolean existsLevelByText(String description) {
        return levelRepository.existsByDescription(description);
    }


    @Transactional
    public ResponseEntity<LevelDto> updateLevel(Long id, LevelDto levelDto) {
        Level existingLevel = levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + id + " does not exist"));

        existingLevel.setDescription(levelDto.getDescription());
        existingLevel.setMaxPoints(levelDto.getMaxPoints());
        existingLevel.setMinPoints(levelDto.getMinPoints());

        Level updatedLevel = levelRepository.save(existingLevel);

        return ResponseEntity.ok(updatedLevel.toLevelDto());

    }

    public Level findById(Long levelId) {
        return levelRepository.findById(levelId).get();
    }

    public void deleteLevel(Long levelId) {
        boolean exists = levelRepository.existsById(levelId);
        if (!exists) throw new IllegalStateException("level with id: " + levelId + " does not exist");
        levelRepository.deleteById(levelId);
    }
}
