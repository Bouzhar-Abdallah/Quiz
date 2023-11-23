package bouzhar.quiz.demo.assignement;

import bouzhar.quiz.demo.assignement.Dtos.AssignementListReqDto;
import bouzhar.quiz.demo.assignement.Dtos.AssignementReqDto;
import bouzhar.quiz.demo.assignement.Dtos.AssignementResDto;
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
    public ResponseEntity<AssignementResDto> addNewAssignement(AssignementReqDto assignementDto) {
        Assignement assignement = modelMapper.map(assignementDto, Assignement.class);

        assignement.setStudent(studentRepository.findById(assignementDto.getStudent_id()).orElseThrow(
                () -> new ResourceNotFoundException("student with id " + assignementDto.getStudent_id() + " not found")
        ));
        assignement.setTest(testRepository.findById(assignementDto.getTest_id()).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + assignementDto.getTest_id() + " not found")
        ));

        return ResponseEntity.ok(
                modelMapper.map(assignementRepository.save(assignement), AssignementResDto.class)
        );
    }

    @Override
    public ResponseEntity<List<AssignementResDto>> addAssignementList(AssignementListReqDto testDto) {
        List<AssignementResDto> assignementResDtos = new ArrayList<>();
        testDto.getStudent_ids().forEach(student_id -> {
            AssignementReqDto assignementReqDto = modelMapper.map(testDto, AssignementReqDto.class);

            Assignement assignement = modelMapper.map(assignementReqDto, Assignement.class);

            assignement.setStudent(studentRepository.findById(student_id).orElseThrow(
                    () -> new ResourceNotFoundException("student with id " + student_id + " not found")
            ));
            assignement.setTest(testRepository.findById(assignementReqDto.getTest_id()).orElseThrow(
                    () -> new ResourceNotFoundException("test with id " + assignementReqDto.getTest_id() + " not found")
            ));
            assignementRepository.save(assignement);

            assignementResDtos.add(modelMapper.map(assignement, AssignementResDto.class ));
        });
        return ResponseEntity.ok(assignementResDtos);
    }

    @Override
    public ResponseEntity<AssignementResDto> getAssignement(Long id) {
        Assignement assignement = assignementRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("assignement with id " + id + " not found")
        );
        return ResponseEntity.ok(
                modelMapper.map(assignement, AssignementResDto.class)
        );
    }

    @Override
    public ResponseEntity<List<AssignementResDto>> getAllAssignements() {
        return ResponseEntity.ok(
                assignementRepository.findAll().stream()
                        .map(assignement -> modelMapper.map(assignement, AssignementResDto.class))
                        .toList()
        );
    }

    @Override
    public ResponseEntity<AssignementResDto> updateAssignement(AssignementReqDto assignementDto) {
        assignementRepository.findById(assignementDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("assignement with id " + assignementDto.getId() + " not found")
        );
        testRepository.findById(assignementDto.getTest_id()).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + assignementDto.getTest_id() + " not found")
        );
        studentRepository.findById(assignementDto.getStudent_id()).orElseThrow(
                () -> new ResourceNotFoundException("student with id " + assignementDto.getStudent_id() + " not found")
        );
        return ResponseEntity.ok(
                modelMapper.map(
                        assignementRepository.save(modelMapper.map(assignementDto, Assignement.class))
                        , AssignementResDto.class)
        );
    }

    @Override
    public ResponseEntity<AssignementResDto> deleteAssignement(Long id) {
        Assignement assignement = assignementRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("assignement with id " + id + " not found")
        );
        assignementRepository.deleteById(id);
        return ResponseEntity.ok(
                modelMapper.map(assignement, AssignementResDto.class)
        );
    }


}
