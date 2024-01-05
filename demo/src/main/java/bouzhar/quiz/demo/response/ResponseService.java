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
import bouzhar.quiz.demo.question.enums.QuestionType;
import bouzhar.quiz.demo.response.dto.ResponseReqDto;
import bouzhar.quiz.demo.response.dto.ResponseResDto;
import bouzhar.quiz.demo.test.TestRepository;
import bouzhar.quiz.demo.validation.Validation;
import bouzhar.quiz.demo.validation.ValidationId;
import bouzhar.quiz.demo.validation.ValidationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    private final TestRepository testRepository;
    private final ResponseRepository responseRepository;
    private final AssignmentRepository assignmentRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ValidationRepository validationRepository;
    //private final ModelMapper modelMapper;

    public ResponseService(TestRepository testRepository, ResponseRepository responseRepository, AssignmentRepository assignmentRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, ValidationRepository validationRepository, ModelMapper modelMapper) {
        this.testRepository = testRepository;
        this.responseRepository = responseRepository;
        this.assignmentRepository = assignmentRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.validationRepository = validationRepository;
        //this.modelMapper = modelMapper;
    }

    public ResponseReqDto create(ResponseReqDto responseReqDto) {

        Assignment assignment = assignmentRepository.findById(responseReqDto.getAssignment_id()).orElseThrow(
                () -> new ResourceNotFoundException("Assignment with id: " + responseReqDto.getAssignment_id() + " not found")
        );
        Question question = questionRepository.findById(responseReqDto.getQuestion_id()).orElseThrow(
                () -> new ResourceNotFoundException("Question with id: " + responseReqDto.getQuestion_id() + " not found")
        );
        List<Answer> answers = answerRepository.findAllById(responseReqDto.getAnswer_ids()).stream().toList();


        if (question.getType().equals(QuestionType.Single) && responseReqDto.getAnswer_ids().size() > 1)
            throw new RuntimeException("this question may only accept one response");

        if (responseReqDto.getAnswer_ids().isEmpty()){
            ResponseId responseId = new ResponseId();
            responseId.setAssignmentId(assignment.getId());
            responseId.setQuestionId(question.getId());
            responseId.setAnswerId(null);
            Response response = new Response();
            response.setId(responseId);
            response.setAssignment(assignment);
            response.setValidation(null);
            responseRepository.save(response);
        }

        for (Answer answer : answers) {
            Validation validation = validationRepository.findById(new ValidationId(question.getId(), answer.getId())).orElseThrow(
                    () -> new ResourceNotFoundException("Validation with id: " + question.getId() + " " + answer.getId() + " not found")
            );
            //assignment.setResponses(List.of(new Response()));
            ResponseId responseId = new ResponseId();
            responseId.setAssignmentId(assignment.getId());
            responseId.setQuestionId(question.getId());
            responseId.setAnswerId(answer.getId());

            Response response = new Response();
            response.setId(responseId);
            response.setAssignment(assignment);
            response.setValidation(validation);

            responseRepository.save(response);

            assignment.setObtainedScore(assignment.getObtainedScore() + validation.getScore());
        }

        updateAssignment(assignment);
        return responseReqDto;
    }


    private void updateAssignment(Assignment assignment){
        if (
                responseRepository.countDistinctQuestionsByAssignmentId(assignment.getId()) == assignment.getTest().getTemporizations().size()
        ) {
            assignment.getTest().setShowResults(true);
            assignment.setIsPassed(true);
            testRepository.save(assignment.getTest());
            if (assignment.getObtainedScore() >= assignment.getTest().getSuccessScore()) {
                assignment.setResult(true);
            }
        }
    }





    /*public ResponseReqDto create(ResponseReqDto responseReqDto) {
        Assignment assignment = assignmentRepository.findById(responseReqDto.getAssignment_id()).orElseThrow(
                () -> new ResourceNotFoundException("Assignment with id: " + responseReqDto.getAssignment_id() + " not found")
        );
        Question question = questionRepository.findById(responseReqDto.getQuestion_id()).orElseThrow(
                () -> new ResourceNotFoundException("Question with id: " + responseReqDto.getQuestion_id() + " not found")
        );

        Answer answer = answerRepository.findById(responseReqDto.getAnswer_id()).orElseThrow(
                () -> new ResourceNotFoundException("Answer with id: " + responseReqDto.getAnswer_id() + " not found")
        );
        Validation validation = validationRepository.findById(new ValidationId(question.getId(), answer.getId())).orElseThrow(
                () -> new ResourceNotFoundException("Validation with id: " + question.getId() + " " + answer.getId() + " not found")
        );
        assignment.setResponses(List.of(new Response()));
        ResponseId responseId = new ResponseId();
        responseId.setAssignmentId(assignment.getId());
        responseId.setQuestionId(question.getId());
        responseId.setAnswerId(answer.getId());

        Response response = new Response();
        response.setId(responseId);
        response.setAssignment(assignment);
        response.setValidation(validation);


        responseRepository.save(response);

        assignment.setObtainedScore(assignment.getObtainedScore() + validation.getScore());
        if (
                responseRepository.countDistinctQuestionsByAssignmentId(assignment.getId()) == assignment.getTest().getTemporizations().size()
        ) {
            assignment.getTest().setShowResults(true);
            assignment.setIsPassed(true);
            testRepository.save(assignment.getTest());
            if (assignment.getObtainedScore() >= assignment.getTest().getSuccessScore()) {
                assignment.setResult(true);
            }
        }
        return responseReqDto;
    }*/
}
