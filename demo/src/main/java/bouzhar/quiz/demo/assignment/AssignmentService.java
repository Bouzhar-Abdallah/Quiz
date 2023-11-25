package bouzhar.quiz.demo.assignment;

import bouzhar.quiz.demo.assignment.Dtos.AssignmentListReqDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentReqDto;
import bouzhar.quiz.demo.assignment.Dtos.AssignmentResDto;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.student.StudentRepository;
import bouzhar.quiz.demo.test.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService implements AssignmentServiceSpecification {
    private final AssignmentRepository assignmentRepository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, TestRepository testRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.assignmentRepository = assignmentRepository;
        this.testRepository = testRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

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
    public AssignmentResDto getAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("assignment with id " + id + " not found")
        );
        return
                modelMapper.map(assignment, AssignmentResDto.class);
    }

    // get all assignments
    @Override
    public List<AssignmentResDto> getAllAssignments() {
        return
                assignmentRepository.findAll().stream()
                        .map(assignment -> modelMapper.map(assignment, AssignmentResDto.class))
                        .toList();
    }

    // update assignment
    @Override
    public AssignmentResDto updateAssignment(AssignmentReqDto assignmentDto) {
        assignmentRepository.findById(assignmentDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("assignment with id " + assignmentDto.getId() + " not found")
        );
        testRepository.findById(assignmentDto.getTest_id()).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + assignmentDto.getTest_id() + " not found")
        );
        studentRepository.findById(assignmentDto.getStudent_id()).orElseThrow(
                () -> new ResourceNotFoundException("student with id " + assignmentDto.getStudent_id() + " not found")
        );
        return
                modelMapper.map(
                        assignmentRepository.save(modelMapper.map(assignmentDto, Assignment.class))
                        , AssignmentResDto.class);
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

            assignmentResDtos.add(modelMapper.map(assignment, AssignmentResDto.class ));
        });
        return assignmentResDtos;
    }


}
