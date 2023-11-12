package bouzhar.quiz.demo.validation;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.answer.AnswerRepository;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {
    private final ValidationRepository validationRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public ValidationService(ValidationRepository validationRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.validationRepository = validationRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    @Transactional
    public Validation addValidation(Long questionId, Answer answer, boolean isCorrect) {
        // Retrieve Question entity from repository
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

        // You might need to adjust this based on your Answer entity structure
        Answer persistedAnswer;

        if (answer.getId() == 0) {
            // If the Answer is new (not persisted yet), save it
            persistedAnswer = answerRepository.save(answer);
        } else {
            // If the Answer is already persisted, load it from the repository
            persistedAnswer = answerRepository.findById(answer.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Answer not found with id: " + answer.getId()));
        }

        // Create a new Validation entity
        Validation validation = new Validation();
        validation.setValidationId(new ValidationId(questionId, persistedAnswer.getId()));
        validation.setQuestion(question);
        validation.setAnswer(persistedAnswer);
        validation.setCorrect(isCorrect);

        // Update the bidirectional relationship on both sides
        persistedAnswer.getValidations().add(validation);
        // If you are using CascadeType.REMOVE, consider removing the following line
        validation.setAnswer(persistedAnswer);

        // Save the Validation entity
        Validation savedValidation = validationRepository.save(validation);

        return new Validation();
    }

    public List<Validation> getValidations() {
        return validationRepository.findAll();
    }
    public void deleteValidation(Long questionId,Long answerId){
        ValidationId validationId = new ValidationId(questionId,answerId);
        boolean exists = validationRepository.existsById(validationId);
        if(!exists){
            throw new ResourceNotFoundException("this relation doesn't exist does not exists");
        }
        validationRepository.deleteById(validationId);
    }
}
