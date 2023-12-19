package bouzhar.quiz.demo.validation;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.answer.AnswerRepository;
import bouzhar.quiz.demo.answer.AnswerService;
import bouzhar.quiz.demo.answer.dto.AnswerSimpleDto;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.QuestionRepository;
import bouzhar.quiz.demo.question.dto.QuestionSimpleDto;
import bouzhar.quiz.demo.validation.Dto.ValidationReqDto;
import bouzhar.quiz.demo.validation.Dto.ValidationResDto;
import bouzhar.quiz.demo.validation.exceptions.ValidationPointsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidationServiceTest {
    @Mock private ValidationService validationService;
    @Mock private AnswerRepository answerRepository;
    @Mock private QuestionRepository questionRepository;
    @Mock private ValidationRepository validationRepository;
    @Mock private ModelMapper modelMapper;
    @Mock Validation validation;
    @Mock ValidationReqDto validationReqDto;
    @Mock Question question;
    @Mock Answer answer;
    @Mock ValidationResDto validationResDto;
    @Mock Level level;
    @Mock QuestionSimpleDto questionSimpleDto;
    @Mock AnswerSimpleDto answerSimpleDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validationService = new ValidationService(validationRepository,questionRepository,answerRepository,modelMapper);
    }
    @Test
    void addValidation_Question_NotExisting_throws() {
        validationReqDto.setQuestion_id(1L);
        validationReqDto.setAnswer_id(1L);
        when(questionRepository.findById(validationReqDto.getQuestion_id())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> validationService.addValidation(validationReqDto));
        verify(questionRepository,times(1)).findById(validationReqDto.getQuestion_id());
    }
    @Test
    void addValidation_Answer_NotExisting_throws() {
        validationReqDto.setAnswer_id(1L);
        validationReqDto.setQuestion_id(1L);
        when(answerRepository.findById(validationReqDto.getAnswer_id())).thenReturn(Optional.empty());
        when(questionRepository.findById(validationReqDto.getQuestion_id())).thenReturn(Optional.of(question));
        assertThrows(ResourceNotFoundException.class, () -> validationService.addValidation(validationReqDto));
        verify(answerRepository,times(1)).findById(validationReqDto.getAnswer_id());
    }

    @Test
    void addValidation_Total_score_exception() {

        when(questionRepository.findById(any())).thenReturn(Optional.of(question));
        when(answerRepository.findById(any())).thenReturn(Optional.of(answer));
        when(validationRepository.save(any())).thenReturn(validation);
        when(question.getLevel()).thenReturn(level);
        when(questionRepository.findById(any())).thenReturn(Optional.of(question));
        when(answerRepository.findById(any())).thenReturn(Optional.of(answer));
        when(level.getMaxPoints()).thenReturn(10f);
        when(question.getValidations()).thenReturn(List.of(validation));
        when(validation.getScore()).thenReturn(12f);
        assertThrows(ValidationPointsException.class, () -> validationService.addValidation(validationReqDto));
    }

    @Test
    void deleteValidation() {
    }
}