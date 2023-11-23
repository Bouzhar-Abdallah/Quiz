package bouzhar.quiz.demo.assignement;

import bouzhar.quiz.demo.assignement.Dtos.AssignementListReqDto;
import bouzhar.quiz.demo.assignement.Dtos.AssignementReqDto;
import bouzhar.quiz.demo.assignement.Dtos.AssignementResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/assignement")
public class AssignementController {
    private final AssignementService assignementService;

    @Autowired
    public AssignementController(AssignementService assignementService) {
        this.assignementService = assignementService;
    }
    @PostMapping(path = "addAssignement")
    public ResponseEntity<AssignementResDto> addNewAssignement(@RequestBody @Valid AssignementReqDto assignementDto){
        return assignementService.addNewAssignement(assignementDto);
    }
    @PostMapping(path = "addAssignementList")
    public ResponseEntity<List<AssignementResDto>> addNewAssignement(@RequestBody @Valid AssignementListReqDto assignementDto){
        return assignementService.addAssignementList(assignementDto);
    }
    @GetMapping(path = "{assignement_id}")
    public ResponseEntity<AssignementResDto> getAssignement(@PathVariable Long assignement_id){
        return assignementService.getAssignement(assignement_id);
    }
    @GetMapping(path = "getall")
    public ResponseEntity<List<AssignementResDto>> getAll(){
        return assignementService.getAllAssignements();
    }
    @PutMapping()
    public ResponseEntity<AssignementResDto> updateAssignement(@RequestBody AssignementReqDto assignementDto){
        return assignementService.updateAssignement(assignementDto);
    }

    @DeleteMapping(path = "{assignement_id}")
    public ResponseEntity<AssignementResDto> deleteAssignement(@PathVariable Long assignement_id){
        return assignementService.deleteAssignement(assignement_id);
    }

}
