package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.exception.CustomValidationException;
import bouzhar.quiz.demo.level.dtos.LevelResDto;
import bouzhar.quiz.demo.level.dtos.LevelSimpleDto;
import bouzhar.quiz.demo.level.exceptions.LevelPointsException;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
class LevelServiceTest {

    @Mock
    private LevelRepository levelRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Level level;
    @Mock
    private LevelSimpleDto levelSimpleDto;
    @Mock
    private LevelService levelService;
    @Mock
    private Question question;
    @Mock
    private Level existingLevel;
    @Mock
    private QuestionResDto questionResDto;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        levelService = new LevelService(levelRepository, modelMapper);
    }

    @Test
    void getLevel() {

        when(levelRepository.findById(1L)).thenReturn(Optional.ofNullable(level));
        when(modelMapper.map(level, LevelSimpleDto.class)).thenReturn(new LevelSimpleDto());
        when(modelMapper.map(level, LevelResDto.class)).thenReturn(new LevelResDto());
        LevelResDto level1 = levelService.getLevel(1L);
        assertThat(level1).isNotNull();
        verify(levelRepository, times(1)).findById(1L);

    }

    @Test
    void getLevelNotFound() {

        when(levelRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> levelService.getLevel(1L));
        verify(levelRepository, times(1)).findById(1L);

    }

    @Test
    void getLevels() {


        when(levelRepository.findAll()).thenReturn(List.of(level));
        when(modelMapper.map(level, LevelSimpleDto.class)).thenReturn(new LevelSimpleDto());
        List<LevelResDto> responseEntity = levelService.getLevels();
        assertThat(responseEntity).isNotNull();
        verify(levelRepository, times(1)).findAll();
    }
    @Test
    void addNewLevel_ValidInput_ReturnsLevelSimpleDto() {
        // Arrange
        LevelSimpleDto levelSimpleDto = new LevelSimpleDto();
        levelSimpleDto.setDescription("New Level");
        levelSimpleDto.setMinPoints(50F);
        levelSimpleDto.setMaxPoints(100F);

        when(levelRepository.save(any())).thenReturn(level);
        when(modelMapper.map(levelSimpleDto, Level.class)).thenReturn(level);
        when(modelMapper.map(level, LevelSimpleDto.class)).thenReturn(levelSimpleDto);

        // Act
        LevelSimpleDto createdLevelSimpleDto = levelService.addNewLevel(levelSimpleDto);

        // Assert
        assertThat(createdLevelSimpleDto).isNotNull();
        assertEquals(levelSimpleDto.getDescription(), createdLevelSimpleDto.getDescription());
        assertEquals(levelSimpleDto.getMinPoints(), createdLevelSimpleDto.getMinPoints());
        assertEquals(levelSimpleDto.getMaxPoints(), createdLevelSimpleDto.getMaxPoints());

        // Verify
        verify(levelRepository, times(1)).save(any());
    }

    @Test
    void addNewLevel_LevelWithSameTextExists_ThrowsCustomValidationException() {
        // Arrange
        LevelSimpleDto levelSimpleDto = new LevelSimpleDto();
        levelSimpleDto.setDescription("Existing Level");
        levelSimpleDto.setMinPoints(50F);
        levelSimpleDto.setMaxPoints(100F);

        when(levelService.existsLevelByText("Existing Level")).thenReturn(true);

        // Act & Assert
        assertThrows(CustomValidationException.class, () -> levelService.addNewLevel(levelSimpleDto));

        // Verify
        verify(levelRepository, never()).save(any());
    }

    @Test
    void addNewLevel_MinPointsGreaterThanMaxPoints_ThrowsLevelPointsException() {
        // Arrange
        LevelSimpleDto levelSimpleDto = new LevelSimpleDto();
        levelSimpleDto.setDescription("New Level");
        levelSimpleDto.setMinPoints(100F);
        levelSimpleDto.setMaxPoints(50F);

        // Act & Assert
        assertThrows(LevelPointsException.class, () -> levelService.addNewLevel(levelSimpleDto));

        // Verify
        verify(levelRepository, never()).save(any());
    }

    @Test
    void addNewLevelAlreadyExists() {
        when(levelRepository.existsByDescription(level.getDescription())).thenReturn(true);
        assertThrows(CustomValidationException.class, () -> levelService.addNewLevel(levelSimpleDto));
        verify(levelRepository, times(1)).existsByDescription(level.getDescription());
    }

    @Test
    void existsLevelByText() {

        when(levelRepository.existsByDescription("test")).thenReturn(true);
        boolean result = levelService.existsLevelByText("test");
        assertThat(result).isTrue();
        verify(levelRepository, times(1)).existsByDescription("test");
    }

    @Test
    void updateLevel_ExistingId_ReturnsUpdatedLevelSimpleDto() {
        // Arrange
        Long id = 1L;
        LevelSimpleDto levelSimpleDto = new LevelSimpleDto();
        levelSimpleDto.setDescription("Updated Description");
        levelSimpleDto.setMaxPoints(10F);
        levelSimpleDto.setMinPoints(5F);

        Level existingLevel = new Level();
        existingLevel.setId(id);
        existingLevel.setDescription("Old Description");
        existingLevel.setMaxPoints(10F);
        existingLevel.setMinPoints(5F);

        when(levelRepository.findById(id)).thenReturn(Optional.of(existingLevel));
        when(levelRepository.save(existingLevel)).thenReturn(existingLevel);

        // Act
        when(modelMapper.map(level, LevelSimpleDto.class)).thenReturn(levelSimpleDto);
        when(levelRepository.save(any())).thenReturn(level);
        LevelSimpleDto updatedLevelSimpleDto = levelService.updateLevel(id, levelSimpleDto);

        // Assert
        assertThat(updatedLevelSimpleDto).isNotNull();


        // Verify
        verify(levelRepository, times(1)).findById(id);
        verify(levelRepository, times(1)).save(existingLevel);
    }

    @Test
    void updateLevel_NotExistingId_ThrowsResourceNotFoundException() {
        // Arrange
        Long nonExistingId = 2L;
        LevelSimpleDto levelSimpleDto = new LevelSimpleDto();

        when(levelRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> levelService.updateLevel(nonExistingId, levelSimpleDto));

        // Verify
        verify(levelRepository, times(1)).findById(nonExistingId);
        verify(levelRepository, never()).save(any()); // Ensure save is not called
    }

    @Test
    void deleteLevel_NotExistingId_ThrowsResourceNotFoundException() {
        // Arrange
        when(levelRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> levelService.deleteLevel(1L));

        // Verify
        verify(levelRepository, times(1)).findById(1L);

    }

    @Test
    void deleteLevel_ExistingId_NoQuestions_DeletesLevel() {
        // Arrange
        when(levelRepository.findById(2L)).thenReturn(Optional.of(level));
        when(level.getQuestions()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertDoesNotThrow(() -> levelService.deleteLevel(2L));

        // Verify
        verify(levelRepository, times(1)).findById(2L);
        verify(level, times(1)).getQuestions();
    }

    @Test
    void deleteLevel_NoQuestions_DeletesLevel() {
        // Arrange
        when(levelRepository.findById(2L)).thenReturn(Optional.of(level));
        when(level.getQuestions()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertDoesNotThrow(() -> levelService.deleteLevel(2L));

        // Verify
        verify(levelRepository, times(1)).findById(2L);
        verify(level, times(1)).getQuestions();
    }

    @Test
    void deleteLevel_QuestionsExist_ThrowsIllegalStateException() {
        // Arrange
        when(levelRepository.findById(2L)).thenReturn(Optional.of(level));
        when(level.getQuestions()).thenReturn(List.of(question));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> levelService.deleteLevel(2L));

        // Verify
        verify(levelRepository, times(1)).findById(2L);
        verify(level, times(1)).getQuestions();
    }

    @Test
    void deleteLevel_ReturnsLevelSimpleDto() {
        // Arrange
        when(modelMapper.map(level, LevelSimpleDto.class)).thenReturn(levelSimpleDto);
        when(levelRepository.findById(2L)).thenReturn(Optional.of(level));
        when(level.getQuestions()).thenReturn(Collections.emptyList());

        // Act
        LevelSimpleDto result = levelService.deleteLevel(2L);

        // Assert
        assertThat(result).isNotNull();

        // Verify
        verify(levelRepository, times(1)).findById(2L);
        verify(level, times(1)).getQuestions();
    }


    @Test
    void getLevelQuestions_ExistingId_ReturnsQuestions() {
        // Arrange
        when(modelMapper.map(question, QuestionResDto.class)).thenReturn(new QuestionResDto());
        when(level.getQuestions()).thenReturn(List.of(question));
        when(levelRepository.findById(1L)).thenReturn(Optional.of(level));

        // Act
        List<QuestionResDto> existingLevelQuestions = levelService.getLevelQuestions(1L);

        // Assert
        assertThat(existingLevelQuestions).isNotNull();

        // Verify
        verify(levelRepository, times(1)).findById(1L);
        verify(level, times(1)).getQuestions();
    }

    @Test
    void getLevelQuestions_NotExistingId_ThrowsResourceNotFoundException() {
        // Arrange
        when(levelRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> levelService.getLevelQuestions(2L));

        // Verify
        verify(levelRepository, times(1)).findById(2L);
        verify(level, never()).getQuestions();
    }

}