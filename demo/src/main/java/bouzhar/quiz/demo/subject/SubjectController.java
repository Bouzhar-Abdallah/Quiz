package bouzhar.quiz.demo.subject;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Subject addSubject(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }
}
