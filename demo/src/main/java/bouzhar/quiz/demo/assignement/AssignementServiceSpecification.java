package bouzhar.quiz.demo.assignement;


import bouzhar.quiz.demo.assignement.Dtos.AssignementListReqDto;
import bouzhar.quiz.demo.assignement.Dtos.AssignementReqDto;
import bouzhar.quiz.demo.assignement.Dtos.AssignementResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignementServiceSpecification {
    ResponseEntity<AssignementResDto> addNewAssignement(AssignementReqDto testDto);
    ResponseEntity<List<AssignementResDto>> addAssignementList(AssignementListReqDto testDto);

    ResponseEntity<AssignementResDto> updateAssignement(AssignementReqDto testDto);

    ResponseEntity<AssignementResDto> deleteAssignement(Long Id);

    ResponseEntity<AssignementResDto> getAssignement(Long Id);
    ResponseEntity<List<AssignementResDto>> getAllAssignements();
}
