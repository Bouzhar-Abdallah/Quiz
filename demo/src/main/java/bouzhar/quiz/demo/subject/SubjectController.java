package bouzhar.quiz.demo.subject;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @GetMapping(path = "getSubjects")
    public List<Subject> getSubjects(){
        return subjectService.getSubjects();
    }
    @PostMapping(path = "addSubject")
    public ResponseEntity<Subject> addSubject(@RequestBody @Valid Subject subject){
        return subjectService.addSubject(subject);
    }
    @PutMapping
    public ResponseEntity<Subject> updateSubject(@RequestBody @Valid Subject subject){
        return subjectService.updateSubject(subject);
    }
    @DeleteMapping(path = "{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable("subjectId") Long subjectId){
        return subjectService.deleteSubject(subjectId);
    }
}
