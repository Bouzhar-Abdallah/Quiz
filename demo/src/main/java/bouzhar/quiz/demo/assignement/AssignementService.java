package bouzhar.quiz.demo.assignement;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.student.StudentRepository;
import bouzhar.quiz.demo.test.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignementService implements AssignementServiceSpecification {
    private final AssignementRepository assignementRepository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AssignementService(AssignementRepository assignementRepository, TestRepository testRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.assignementRepository = assignementRepository;
        this.testRepository = testRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseEntity<AssignementDto> addNewAssignement(AssignementDto assignementDto) {
        testRepository.findById(assignementDto.getTest().getId()).orElseThrow(
                ()-> new ResourceNotFoundException("test with id "+ assignementDto.getTest().getId() +" not found")
        );
        studentRepository.findById(assignementDto.getStudent().getId()).orElseThrow(
                ()-> new ResourceNotFoundException("student with id "+ assignementDto.getStudent().getId() +" not found")
        );
        return ResponseEntity.ok(
                modelMapper.map(assignementRepository.save(modelMapper.map(assignementDto, Assignement.class)), AssignementDto.class)
        );
    }

    @Override
    public ResponseEntity<AssignementDto> getAssignement(Long id) {
        Assignement assignement = assignementRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("assignement with id "+ id +" not found")
        );
        return ResponseEntity.ok(
                modelMapper.map(assignement, AssignementDto.class)
        );
    }
    @Override
    public ResponseEntity<List<AssignementDto>> getAllAssignements() {
        return ResponseEntity.ok(
                assignementRepository.findAll().stream()
                        .map(assignement -> modelMapper.map(assignement, AssignementDto.class))
                        .toList()
        );
    }
    @Override
    public ResponseEntity<AssignementDto> updateAssignement(AssignementDto assignementDto) {
        assignementRepository.findById(assignementDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("assignement with id "+ assignementDto.getId() +" not found")
        );
        testRepository.findById(assignementDto.getTest().getId()).orElseThrow(
                ()-> new ResourceNotFoundException("test with id "+ assignementDto.getTest().getId() +" not found")
        );
        studentRepository.findById(assignementDto.getStudent().getId()).orElseThrow(
                ()-> new ResourceNotFoundException("student with id "+ assignementDto.getStudent().getId() +" not found")
        );
        return ResponseEntity.ok(
                modelMapper.map(
                        assignementRepository.save(modelMapper.map(assignementDto, Assignement.class))
                        , AssignementDto.class)
        );
    }

    @Override
    public ResponseEntity<AssignementDto> deleteAssignement(Long id) {
        Assignement assignement = assignementRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("assignement with id "+ id +" not found")
        );
        assignementRepository.deleteById(id);
        return ResponseEntity.ok(
                modelMapper.map(assignement, AssignementDto.class)
        );
    }


}
