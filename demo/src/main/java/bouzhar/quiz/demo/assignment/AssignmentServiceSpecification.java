package bouzhar.quiz.demo.assignment;


import bouzhar.quiz.demo.assignment.Dtos.AssignmentListReqDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentReqDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignmentServiceSpecification {
    AssignmentResDto addNewAssignment(AssignmentReqDto testDto);
    AssignmentResDto getAssignment(Long Id);
    List<AssignmentResDto> getAllAssignments();
    AssignmentResDto updateAssignment(AssignmentReqDto testDto);
    AssignmentResDto deleteAssignment(Long Id);
    List<AssignmentResDto> addAssignmentList(AssignmentListReqDto testDto);
}
