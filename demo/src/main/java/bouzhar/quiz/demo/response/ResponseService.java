package bouzhar.quiz.demo.response;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.answer.AnswerRepository;
import bouzhar.quiz.demo.answer.AnswerService;
import bouzhar.quiz.demo.assignment.Assignment;
import bouzhar.quiz.demo.assignment.AssignmentRepository;
import bouzhar.quiz.demo.assignment.AssignmentService;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.QuestionRepository;
import bouzhar.quiz.demo.question.QuestionService;
import bouzhar.quiz.demo.response.dto.ResponseReqDto;
import bouzhar.quiz.demo.validation.Validation;
import bouzhar.quiz.demo.validation.ValidationId;
import bouzhar.quiz.demo.validation.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;
    private final AssignmentRepository assignmentRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ValidationRepository validationRepository;

    @Autowired
    public ResponseService(ValidationRepository validationRepository,ResponseRepository responseRepository, AssignmentRepository assignmentRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.responseRepository = responseRepository;
        this.assignmentRepository = assignmentRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.validationRepository = validationRepository;
    }

    public ResponseReqDto create(ResponseReqDto responseReqDto) {
        Assignment assignment = assignmentRepository.findById(responseReqDto.getAssignment_id()).orElseThrow(
                () -> new ResourceNotFoundException("Assignment with id: "+ responseReqDto.getAssignment_id() +" not found")
        );
        Question question = questionRepository.findById(responseReqDto.getQuestion_id()).orElseThrow(
                () -> new ResourceNotFoundException("Question with id: "+ responseReqDto.getQuestion_id() +" not found")
        );
        Answer answer = answerRepository.findById(responseReqDto.getAnswer_id()).orElseThrow(
                () -> new ResourceNotFoundException("Answer with id: "+ responseReqDto.getAnswer_id() +" not found")
        );
        Validation validation = validationRepository.findById(new ValidationId(question.getId(), answer.getId())).orElseThrow(
                () -> new ResourceNotFoundException("Validation with id: "+ question.getId() + " " + answer.getId() +" not found")
        );
        ResponseId responseId = new ResponseId();
        responseId.setAssignmentId(assignment.getId());  // Assuming assignment has an id
        responseId.setQuestionId(question.getId());      // Assuming question has an id
        responseId.setAnswerId(answer.getId());

        Response response = new Response();
        response.setId(responseId);
        response.setAssignment(assignment);
        response.setValidation(validation);
        responseRepository.save(response);
        return responseReqDto;
    }
}
