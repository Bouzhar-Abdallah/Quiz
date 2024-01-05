package bouzhar.quiz.demo.assignment;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.answer.dto.AnswerSimpleDto;
import bouzhar.quiz.demo.answer.dto.AnswerWithValidation;
import bouzhar.quiz.demo.assignment.Dtos.*;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.dto.QuestionSimpleDto;
import bouzhar.quiz.demo.question.dto.QuestionWithSimpleAnswers;
import bouzhar.quiz.demo.question.dto.QuestionWithValidations;
import bouzhar.quiz.demo.response.Response;
import bouzhar.quiz.demo.response.dto.ResponseResDto;
import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.student.StudentRepository;
import bouzhar.quiz.demo.temporization.Temporization;
import bouzhar.quiz.demo.temporization.TemporizationResDto;
import bouzhar.quiz.demo.test.Test;
import bouzhar.quiz.demo.test.TestRepository;
import bouzhar.quiz.demo.validation.Dto.ValidationResDto;
import bouzhar.quiz.demo.validation.Validation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AssignmentService implements AssignmentServiceSpecification {
    private final AssignmentRepository assignmentRepository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    /*
     *
     * Methods
     *
     * */

    // add new assignment
    @Override
    public AssignmentResDto addNewAssignment(AssignmentReqDto assignmentDto) {
        Assignment assignment = modelMapper.map(assignmentDto, Assignment.class);

        assignment.setStudent(studentRepository.findById(assignmentDto.getStudent_id()).orElseThrow(
                () -> new ResourceNotFoundException("student with id " + assignmentDto.getStudent_id() + " not found")
        ));
        assignment.setTest(testRepository.findById(assignmentDto.getTest_id()).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + assignmentDto.getTest_id() + " not found")
        ));

        return
                modelMapper.map(assignmentRepository.save(assignment), AssignmentResDto.class);
    }

    // get assignment by id
    @Override
    public PassQuiz getAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("assignment with id " + id + " not found")
        );
        PassQuiz passQuiz = modelMapper.map(assignment, PassQuiz.class);
        passQuiz.setAnsweredQuestions(
                convertResponsesToQuestions(passQuiz.getResponses())
        );
        passQuiz.setTemporizations(assignment.getTest().getTemporizations().stream().map(
                temporization -> modelMapper.map(temporization, TemporizationResDto.class)).toList());
        return passQuiz;
    }

    public List<QuestionWithSimpleAnswers> convertResponsesToQuestions(List<ResponseResDto> responses) {
        Map<QuestionSimpleDto, List<ValidationResDto>> validationsByQuestion = responses.stream()
                .map(ResponseResDto::getValidation)
                .collect(Collectors.groupingBy(ValidationResDto::getQuestion));
        return validationsByQuestion.entrySet().stream().map((entry -> {
            QuestionSimpleDto question = entry.getKey();
            List<AnswerSimpleDto> answers = entry.getValue().stream().map(ValidationResDto::getAnswer).toList();
            return new QuestionWithSimpleAnswers(modelMapper.map(question, QuestionSimpleDto.class),answers);
        })).toList();
    }

    // get all assignments
    @Override
    public List<AssignmentResDto> getAllAssignments() {
        return
                assignmentRepository.findAll().stream()
                        .map(assignment -> modelMapper.map(assignment, AssignmentResDto.class))
                        .toList();
    }
    // get a test all assignments

    public List<AssignmentResDto> getTestAllAssignments(Long test_id) {
        testRepository.findById(test_id).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + test_id + " not found"));

        return
                assignmentRepository.findAllByTestIdOrderByChanceAsc(test_id).stream()
                        .map(assignment -> modelMapper.map(assignment, AssignmentResDto.class))
                        .toList();
    }

    // update assignment
    @Override
    public AssignmentResDto updateAssignment(AssignmentReqDto assignmentDto) {
        Assignment assignment = assignmentRepository.findById(assignmentDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("assignment with id " + assignmentDto.getId() + " not found")
        );
        Test test = testRepository.findById(assignmentDto.getTest_id()).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + assignmentDto.getTest_id() + " not found")
        );
        Student student = studentRepository.findById(assignmentDto.getStudent_id()).orElseThrow(
                () -> new ResourceNotFoundException("student with id " + assignmentDto.getStudent_id() + " not found")
        );
        assignment.setTest(test);
        assignment.setStudent(student);
        assignment.setChance(assignmentDto.getChance());
        assignment.setEndDate(assignmentDto.getEndDate());
        assignment.setStartDate(assignmentDto.getStartDate());
        assignment.setObtainedScore(assignmentDto.getObtainedScore());
        assignment.setResult(assignmentDto.isResult());
        assignment.setReason(assignmentDto.getReason());

        return
                modelMapper.map(assignmentRepository.save(assignment), AssignmentResDto.class);
    }

    // delete assignment
    @Override
    public AssignmentResDto deleteAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("assignment with id " + id + " not found")
        );
        assignmentRepository.deleteById(id);
        return
                modelMapper.map(assignment, AssignmentResDto.class);
    }

    // add new assignment list
    @Override
    public List<AssignmentResDto> addAssignmentList(AssignmentListReqDto testDto) {
        List<AssignmentResDto> assignmentResDtos = new ArrayList<>();
        testDto.getStudent_ids().forEach(student_id -> {
            AssignmentReqDto assignmentReqDto = modelMapper.map(testDto, AssignmentReqDto.class);

            Assignment assignment = modelMapper.map(assignmentReqDto, Assignment.class);

            assignment.setStudent(studentRepository.findById(student_id).orElseThrow(
                    () -> new ResourceNotFoundException("student with id " + student_id + " not found")
            ));
            assignment.setTest(testRepository.findById(assignmentReqDto.getTest_id()).orElseThrow(
                    () -> new ResourceNotFoundException("test with id " + assignmentReqDto.getTest_id() + " not found")
            ));
            assignmentRepository.save(assignment);

            assignmentResDtos.add(modelMapper.map(assignment, AssignmentResDto.class));
        });
        return assignmentResDtos;
    }

    @Override
    public Page<AssignmentResDto> getPaginatedAssignments(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return assignmentRepository.findAll(pageRequest).map(assignment -> modelMapper.map(assignment, AssignmentResDto.class));
    }


    public List<QuestionWithValidations> getAssignmentCorrection(Long id) {
    Assignment assignment = assignmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("assignment with id " + id + " not found")
    );

    List<QuestionWithSimpleAnswers> answeredQuestions = convertResponsesToQuestions(
            assignment.getResponses().stream().map(
                    response -> modelMapper.map(response,ResponseResDto.class)
            ).toList()
    );

    return assignment.getTest().getTemporizations().stream().map(temporization -> {
        QuestionWithValidations questionWithValidations = new QuestionWithValidations();
        questionWithValidations.setQuestion(modelMapper.map(temporization.getQuestion(),QuestionSimpleDto.class));

        List<AnswerWithValidation> answersWithValidation = temporization.getQuestion().getValidations().stream().map(validation -> {
            AnswerWithValidation answerWithValidation = new AnswerWithValidation();
            answerWithValidation.setAnswer(modelMapper.map(validation.getAnswer(),AnswerSimpleDto.class));
            answerWithValidation.setValidation(modelMapper.map(validation,ValidationResDto.class));

            boolean isUsed = answeredQuestions.stream()
                    .anyMatch(answeredQuestion -> answeredQuestion.getAnswers().stream()
                            .anyMatch(answer -> answer.getId().equals(validation.getAnswer().getId())));
            answerWithValidation.setIsSelected(isUsed);

            return answerWithValidation;
        }).collect(Collectors.toList());
        questionWithValidations.setAnswersWithValidation(answersWithValidation);

        return questionWithValidations;
    }).collect(Collectors.toList());
}

}
