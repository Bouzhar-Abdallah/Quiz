package bouzhar.quiz.demo.subject;

import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.exception.CustomValidationException;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import bouzhar.quiz.demo.subject.dto.SubjectDto;
import bouzhar.quiz.demo.subject.dto.SubjectReqDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    /*
    *
    * Methods
    *
    * */

    // Add new subject
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectDto addSubject(@RequestBody @Valid SubjectReqDto subjectReqDto) throws CustomValidationException {
        return subjectService.addSubject(subjectReqDto);
    }

    // Get subject by id
    @GetMapping(path = "{subjectId}")
    @ResponseStatus(HttpStatus.OK)
    public SubjectDto getSubject(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubject(subjectId);
    }
    // Get all subjects
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectDto> getSubjects() {
        return subjectService.getSubjects();
    }
    // Update subject
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SubjectDto updateSubject(@RequestBody @Valid SubjectReqDto subjectReqDto) {
        return subjectService.updateSubject(subjectReqDto);
    }

    // Delete subject
    @DeleteMapping(path = "{subjectId}")
    @ResponseStatus(HttpStatus.OK)
    public SubjectDto deleteSubject(@PathVariable("subjectId") Long subjectId) {
        return subjectService.deleteSubject(subjectId);
    }

    // Get subject questions
    @GetMapping(path = "{subjectId}/questions")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionResDto> getSubjectQuestions(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubjectQuestions(subjectId);
    }
    @GetMapping("/pages")
    public Page<SubjectDto> getPaginatedAnswers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return subjectService.getPaginatedAnswers(page, size);
    }
}
