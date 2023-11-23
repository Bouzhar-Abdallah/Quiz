package bouzhar.quiz.demo.validation;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.answer.AnswerRepository;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.QuestionRepository;
import bouzhar.quiz.demo.validation.Dto.ValidationReqDto;
import bouzhar.quiz.demo.validation.Dto.ValidationResDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {
    private final ValidationRepository validationRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ValidationService(ValidationRepository validationRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.validationRepository = validationRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    public List<?> getValidations() {
        return validationRepository.findAll();
    }
    public ResponseEntity<ValidationResDto> deleteValidation(Long questionId,Long answerId){
        ValidationId validationId = new ValidationId(questionId,answerId);
        Validation validation = validationRepository.findById(validationId).orElseThrow(()-> new ResourceNotFoundException("this relation doesn't exist"));
        /*boolean exists = validationRepository.existsById(validationId);
        if(!exists){
            throw new ResourceNotFoundException("this relation doesn't exist does not exists");
        }*/
        validationRepository.deleteById(validationId);
        return ResponseEntity.ok(modelMapper.map(validation, ValidationResDto.class));
    }

    public ResponseEntity<?> addValidation(ValidationReqDto validationDto) {
        Question question = questionRepository.findById(validationDto.getQuestion_id()).orElseThrow(()-> new ResourceNotFoundException("question with id: "+validationDto.getQuestion_id()+" not found"));
        Answer answer = answerRepository.findById(validationDto.getAnswer_id()).orElseThrow(()-> new ResourceNotFoundException("answer with id: "+validationDto.getAnswer_id()+" not found"));

        Validation validation = new Validation();
        validation.setValidationId(new ValidationId(validationDto.getQuestion_id(), validationDto.getAnswer_id()));
        validation.setQuestion(question);
        validation.setAnswer(answer);
        validation.setIsCorrect(validationDto.getIsCorrect());
        validation.setScore(validationDto.getScore());
        return ResponseEntity.ok(modelMapper.map(validationRepository.save(validation), ValidationResDto.class));
    }

}
