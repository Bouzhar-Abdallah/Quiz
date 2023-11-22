package bouzhar.quiz.demo.assignement;

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
    public ResponseEntity<AssignementDto> addNewAssignement(@RequestBody @Valid AssignementDto assignementDto){
        return assignementService.addNewAssignement(assignementDto);
    }
    @GetMapping(path = "{assignement_id}")
    public ResponseEntity<AssignementDto> getAssignement(@PathVariable Long assignement_id){
        return assignementService.getAssignement(assignement_id);
    }
    @GetMapping(path = "getall")
    public ResponseEntity<List<AssignementDto>> addNewAssignement(){
        return assignementService.getAllAssignements();
    }
    @PutMapping()
    public ResponseEntity<AssignementDto> updateAssignement(@RequestBody AssignementDto assignementDto){
        return assignementService.updateAssignement(assignementDto);
    }

    @DeleteMapping(path = "{assignement_id}")
    public ResponseEntity<AssignementDto> deleteAssignement(@PathVariable Long assignement_id){
        return assignementService.deleteAssignement(assignement_id);
    }

}
