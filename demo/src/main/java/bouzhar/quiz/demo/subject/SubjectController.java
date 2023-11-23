package bouzhar.quiz.demo.subject;

import bouzhar.quiz.demo.exception.ValidationException;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getSubjects() {
        return subjectService.getSubjects();
    }

    @GetMapping(path = "{subjectId}")
    public ResponseEntity<?> getSubject(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubject(subjectId);
    }

    @GetMapping(path = "{subjectId}/questions")
    public ResponseEntity<List<QuestionResDto>> getSubjectQuestions(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubjectQuestions(subjectId);
    }

    @PostMapping
    public ResponseEntity<SubjectDto> addSubject(@RequestBody @Valid SubjectDto subjectDto) throws ValidationException {
        return subjectService.addSubject(subjectDto);
    }

    @PutMapping
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody @Valid SubjectDto subjectDto) {
        return subjectService.updateSubject(subjectDto);
    }

    @DeleteMapping(path = "{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable("subjectId") Long subjectId) {
        return subjectService.deleteSubject(subjectId);
    }
}
