package bouzhar.quiz.demo.assignment;

import bouzhar.quiz.demo.assignment.Dtos.AssignmentListReqDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentReqDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    /*
     *
     * Methods
     *
     * */

    // add new assignment
    @PostMapping(path = "addAssignment")
    public AssignmentResDto addNewAssignment(@RequestBody @Valid AssignmentReqDto assignmentDto) {
        return assignmentService.addNewAssignment(assignmentDto);
    }

    // get assignment by id
    @GetMapping(path = "{assignment_id}")
    public AssignmentResDto getAssignment(@PathVariable Long assignment_id) {
        return assignmentService.getAssignment(assignment_id);
    }

    // get all assignments
    @GetMapping(path = "getall")
    public List<AssignmentResDto> getAll() {
        return assignmentService.getAllAssignments();
    }

    // update assignment
    @PutMapping()
    public AssignmentResDto updateAssignment(@RequestBody AssignmentReqDto assignmentDto) {
        return assignmentService.updateAssignment(assignmentDto);
    }

    // delete assignment
    @DeleteMapping(path = "{assignment_id}")
    public AssignmentResDto deleteAssignment(@PathVariable Long assignment_id) {
        return assignmentService.deleteAssignment(assignment_id);
    }

    // add new assignment list
    @PostMapping(path = "addAssignmentList")
    public List<AssignmentResDto> addAssignmentsList(@RequestBody @Valid AssignmentListReqDto assignmentDto) {
        return assignmentService.addAssignmentList(assignmentDto);
    }

}
