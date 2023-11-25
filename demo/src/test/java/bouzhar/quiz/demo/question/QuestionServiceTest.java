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
        questionService = new QuestionService(questionRepository,levelRepository, subjectRepository,mediaService ,modelMapper);
    }

    @Test
    void getQuestions() {

    }
/*
    @Test
    public void testAddQuestion() {
        QuestionReqDto questionReqDto = mock(QuestionReqDto.class);
        questionReqDto.setLevel_id(1L);
        questionReqDto.setSubject_id(2L);


        Level level = new Level();
        when(levelRepository.findById(any())).thenReturn(java.util.Optional.of(level));

        Subject subject = new Subject();
        when(subjectRepository.findById(any())).thenReturn(java.util.Optional.of(subject));

        Question question = new Question();
        when(modelMapper.map(questionReqDto, Question.class)).thenReturn(question);

        Media media = new Media();
        when(questionReqDto.getMedias()).thenReturn(List.of(media));

        when(questionRepository.save(any(Question.class))).thenReturn(question);


        //ResponseEntity<QuestionResDto> response = questionService.addQuestion(questionReqDto);
        try {
            ResponseEntity<QuestionResDto> response = questionService.addQuestion(questionReqDto);

            assertNotNull(response);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace(); // print the stack trace
            fail("Exception thrown: " + e.getMessage());
        }


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
        ResponseEntity<QuestionResDto> responseEntity = questionService.updateQuestion(1L, questionReqDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(questionRepository, times(1)).save(question);
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    void findById() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(modelMapper.map(question, QuestionResDto.class)).thenReturn(questionResDto);
        ResponseEntity<QuestionResDto> responseEntity = questionService.findById(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    void deleteQuestion() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        ResponseEntity<QuestionResDto> responseEntity = questionService.deleteQuestion(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(questionRepository, times(1)).deleteById(1L);
        verify(questionRepository, times(1)).findById(1L);
    }*/
}