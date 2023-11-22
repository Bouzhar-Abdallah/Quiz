package bouzhar.quiz.demo.assignement;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignementServiceSpecification {
    ResponseEntity<AssignementDto> addNewAssignement(AssignementDto testDto);

    ResponseEntity<AssignementDto> updateAssignement(AssignementDto testDto);

    ResponseEntity<AssignementDto> deleteAssignement(Long Id);

    ResponseEntity<AssignementDto> getAssignement(Long Id);
    ResponseEntity<List<AssignementDto>> getAllAssignements();
}
