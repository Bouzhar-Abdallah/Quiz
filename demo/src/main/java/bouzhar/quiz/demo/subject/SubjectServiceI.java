package bouzhar.quiz.demo.subject;

import bouzhar.quiz.demo.question.dto.QuestionResDto;
import bouzhar.quiz.demo.subject.dto.SubjectDto;
import bouzhar.quiz.demo.subject.dto.SubjectReqDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubjectServiceI {
    // Add new subject
    SubjectDto addSubject(SubjectReqDto subjectReqDto);

    // Get subject by id
    SubjectDto getSubject(Long subjectId);

    // Get all subjects
    List<SubjectDto> getSubjects();

    // Update subject
    SubjectDto updateSubject(SubjectReqDto subjectReqDto);

    // Delete subject
    SubjectDto deleteSubject(Long subjectId);

    // Get subject questions
    List<QuestionResDto> getSubjectQuestions(Long subjectId);

    /*
     *
     * helpers
     *
     * */
    boolean existsSubjectByName(String name);

    Page<SubjectDto> getPaginatedAnswers(int page, int size);
}
