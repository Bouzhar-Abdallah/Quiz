package bouzhar.quiz.demo.assignment;


import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentListReqDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentReqDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentResDto;
import bouzhar.quiz.demo.assignment.Dtos.PassQuiz;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignmentServiceSpecification {
    AssignmentResDto addNewAssignment(AssignmentReqDto testDto);
    PassQuiz getAssignment(Long Id);
    List<AssignmentResDto> getAllAssignments();
    AssignmentResDto updateAssignment(AssignmentReqDto testDto);
    AssignmentResDto deleteAssignment(Long Id);
    List<AssignmentResDto> addAssignmentList(AssignmentListReqDto testDto);

    Page<AssignmentResDto> getPaginatedAssignments(int page, int size);
}
