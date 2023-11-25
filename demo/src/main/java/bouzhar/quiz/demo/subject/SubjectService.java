package bouzhar.quiz.demo.subject;

import bouzhar.quiz.demo.exception.CustomValidationException;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import bouzhar.quiz.demo.subject.dto.SubjectDto;
import bouzhar.quiz.demo.subject.dto.SubjectReqDto;
import bouzhar.quiz.demo.subject.dto.SubjectSimpleDto;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements SubjectServiceI {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    /*
     *
     * Methods
     *
     * */

    // Add new subject
    @Override
    public SubjectDto addSubject(SubjectReqDto subjectReqDto) {
        if (existsSubjectByName(subjectReqDto.getName())) {
            throw new CustomValidationException("Subject with the same name already exists");
        }
        Subject newSubject = modelMapper.map(subjectReqDto, Subject.class);

        if (subjectReqDto.getParent_id() != null) {
            Subject parentSubject = subjectRepository.findById(subjectReqDto.getParent_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent subject does not exist"));
            newSubject.setParent(parentSubject);
        }

        return modelMapper.map(subjectRepository.save(newSubject), SubjectDto.class);
    }

    // Get subject by id
    @Override
    public SubjectDto getSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException(
                "Subject not found with id: " + subjectId
        ));
        return modelMapper.map(subject, SubjectDto.class);
    }

    // Get all subjects
    @Override
    public List<SubjectDto> getSubjects() {
        return subjectRepository.findAll().stream()
                .map(subject -> modelMapper.map(subject, SubjectDto.class))
                .toList();
    }

    // Update subject
    @Override
    public SubjectDto updateSubject(SubjectReqDto subjectReqDto) {

        Subject subject = subjectRepository.findById(subjectReqDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectReqDto.getId()));
        if (!subject.getName().equals(subjectReqDto.getName())) {
            if (existsSubjectByName(subjectReqDto.getName())) {
                throw new CustomValidationException("a subject with the same name already exists");
            } else {
                subject.setName(subjectReqDto.getName());
            }
        }

        if (subjectReqDto.getParent_id() != null) {
            Subject parentSubject = subjectRepository.findById(subjectReqDto.getParent_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent subject does not exist"));
            subject.setParent(parentSubject);
        } else {
            subject.setParent(null);
        }

        return modelMapper.map(subjectRepository.save(subject), SubjectDto.class);
    }

    // Delete subject
    @Override
    public SubjectDto deleteSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));
        if (!subject.getChildren().isEmpty()) {
            throw new IllegalStateException("The subject with ID " + subjectId + " has children, deleting it would cause data lose");
        }
        if (!subject.getQuestions().isEmpty()) {
            throw new IllegalStateException("The subject with ID " + subjectId + " has questions, deleting it would cause data lose");
        }
        subjectRepository.deleteById(subjectId);
        return modelMapper.map(subject, SubjectDto.class);
    }

    // Get subject questions
    @Override
    public List<QuestionResDto> getSubjectQuestions(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

        return subject.getQuestions().stream()
                .map(question -> modelMapper.map(question, QuestionResDto.class))
                .toList();
    }

    /*
    *
    * helpers
    *
    * */
    @Override
    public boolean existsSubjectByName(String name) {
        return subjectRepository.existsByName(name);
    }
}
