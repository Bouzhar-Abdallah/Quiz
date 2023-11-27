package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.level.LevelRepository;
import bouzhar.quiz.demo.media.Media;
import bouzhar.quiz.demo.media.MediaService;
import bouzhar.quiz.demo.question.dto.QuestionReqDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.subject.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private LevelRepository levelRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private QuestionService questionService;
    @Mock
    private MediaService mediaService;
    @Mock
    private Question question;
    @Mock
    private QuestionReqDto questionReqDto;
    @Mock
    private QuestionResDto questionResDto;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionService(questionRepository, levelRepository, subjectRepository, modelMapper);
    }

    @Test
    void getQuestions() {
        when(questionRepository.findAll()).thenReturn(List.of(question));
        when(modelMapper.map(question, QuestionResDto.class)).thenReturn(questionResDto);
        List<QuestionResDto> responseEntity = questionService.getQuestions();
        assertThat(responseEntity).isNotNull();
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    public void testAddQuestion() {

        questionReqDto.setLevel_id(1L);
        questionReqDto.setSubject_id(2L);

        Level level = new Level();
        Subject subject = new Subject();
        Question question = new Question();
        QuestionResDto questionResDto = new QuestionResDto();

        // Mocking repository behaviors
        when(levelRepository.findById(any())).thenReturn(Optional.of(level));
        when(subjectRepository.findById(any())).thenReturn(Optional.of(subject));
        when(modelMapper.map(questionReqDto, Question.class)).thenReturn(question);
        when(questionRepository.save(question)).thenReturn(question);
        when(modelMapper.map(question, QuestionResDto.class)).thenReturn(questionResDto);
        Media media = new Media();
        when(questionReqDto.getMedias()).thenReturn(List.of(media));

        // Act
        QuestionResDto result = questionService.addQuestion(questionReqDto);

        // Assert
        assertNotNull(result);
        assertEquals(questionResDto, result);

        // Verify repository interactions
        verify(levelRepository, times(1)).findById(any());
        verify(subjectRepository, times(1)).findById(any());
        verify(questionRepository, times(1)).save(question);

    }

    @Test
    void updateQuestion() {
        QuestionReqDto questionReqDto = mock(QuestionReqDto.class);
        questionReqDto.setLevel_id(1L);
        questionReqDto.setSubject_id(2L);


        Level level = new Level();
        when(levelRepository.findById(any())).thenReturn(java.util.Optional.of(level));

        Subject subject = new Subject();
        when(subjectRepository.findById(any())).thenReturn(java.util.Optional.of(subject));

        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(modelMapper.map(questionReqDto, Question.class)).thenReturn(question);
        when(modelMapper.map(question, QuestionResDto.class)).thenReturn(questionResDto);
        QuestionResDto responseEntity = questionService.updateQuestion(1L, questionReqDto);
        verify(questionRepository, times(1)).save(question);
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    void findById() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(modelMapper.map(question, QuestionResDto.class)).thenReturn(questionResDto);
        QuestionResDto responseEntity = questionService.getQuestion(1L);
        assertThat(responseEntity).isNotNull();
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    void deleteQuestion() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        QuestionResDto responseEntity = questionService.deleteQuestion(1L);
        verify(questionRepository, times(1)).deleteById(1L);
        verify(questionRepository, times(1)).findById(1L);
    }
}