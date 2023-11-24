package bouzhar.quiz.demo.level;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.exception.ValidationException;
import bouzhar.quiz.demo.level.dtos.LevelDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*@ExtendWith(MockitoExtension.class)
 */
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@SpringBootTest
class LevelServiceTest {

    @Mock
    private LevelRepository levelRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Level level;
    @Mock
    private LevelDto levelDto;
    @Mock
    private LevelService levelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        levelService = new LevelService(levelRepository, modelMapper);
    }

    @Test
    void getLevel() {

        when(levelRepository.findById(1L)).thenReturn(Optional.ofNullable(level));
        when(modelMapper.map(level, LevelDto.class)).thenReturn(new LevelDto());
        ResponseEntity<LevelDto> responseEntity = levelService.getLevel(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
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
        when(modelMapper.map(level, LevelDto.class)).thenReturn(new LevelDto());
        ResponseEntity<List<LevelDto>> responseEntity = levelService.getLevels();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        verify(levelRepository, times(1)).findAll();
    }

    @Test
    void addNewLevel() {

        when(levelRepository.save(level)).thenReturn(level);
        when(modelMapper.map(levelDto, Level.class)).thenReturn(level);
        when(modelMapper.map(level, LevelDto.class)).thenReturn(levelDto);
        ResponseEntity<LevelDto> responseEntity = levelService.addNewLevel(levelDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        verify(levelRepository, times(1)).save(level);
    }

    @Test
    void addNewLevelAlreadyExists() {
        when(levelRepository.existsByDescription(level.getDescription())).thenReturn(true);
        assertThrows(ValidationException.class, () -> levelService.addNewLevel(levelDto));
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
    void updateLevel() {
        when(levelRepository.findById(1L)).thenReturn(Optional.ofNullable(level));
        when(levelRepository.save(level)).thenReturn(level);
        when(modelMapper.map(level, LevelDto.class)).thenReturn(levelDto);
        ResponseEntity<LevelDto> responseEntity = levelService.updateLevel(1L, levelDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        verify(levelRepository, times(1)).findById(1L);
        verify(levelRepository, times(1)).save(level);
    }

    @Test
    void deleteLevel() {


        when(levelRepository.findById(1L)).thenReturn(Optional.ofNullable(level));
        doNothing().when(levelRepository).delete(level);
        ResponseEntity<String> responseEntity = levelService.deleteLevel(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        verify(levelRepository, times(1)).findById(1L);
        verify(levelRepository, times(1)).delete(level);
    }

    @Test
    void getLevelQuestions() {

        when(levelRepository.findById(1L)).thenReturn(Optional.ofNullable(level));
        ResponseEntity<List<QuestionResDto>> responseEntity = levelService.getLevelQuestions(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        verify(levelRepository, times(1)).findById(1L);
        verify(level, times(1)).getQuestions();
    }
}