package bouzhar.quiz.demo.subject;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }


    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public ResponseEntity<SubjectDto> addSubject(SubjectDto subjectDto) {
        if (existsSubjectByName(subjectDto.getName())) {
            throw new ValidationException("Subject with the same name already exists");
        }
        if (subjectDto.getParent() != null) {
            if (!subjectRepository.existsById(subjectDto.getParent().getId())) {
                throw new ResourceNotFoundException("Parent subject does not exist");
            }
        }
        Subject createdSubject =subjectRepository.save(modelMapper.map(subjectDto,Subject.class));
        return ResponseEntity.ok(modelMapper.map(createdSubject,SubjectDto.class));
    }

    private boolean existsSubjectByName(String name) {
        return subjectRepository.existsByName(name);
    }

    public ResponseEntity<SubjectDto> updateSubject(SubjectDto subjectDto) {
        if (!subjectRepository.existsById(subjectDto.getId())) {
            throw new ResourceNotFoundException("Subject does not exist");
        }
        if (subjectDto.getParent() != null) {
            if (!subjectRepository.existsById(subjectDto.getParent().getId())) {
                throw new ResourceNotFoundException("Parent subject does not exist");
            }
        }
        Subject updatedSubject = subjectRepository.save(modelMapper.map(subjectDto,Subject.class));
        return ResponseEntity.ok(modelMapper.map(updatedSubject,SubjectDto.class));
    }

    public ResponseEntity<String> deleteSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new ResourceNotFoundException("Subject does not exist");
        }
        subjectRepository.deleteById(subjectId);
        return ResponseEntity.ok("Subject deleted");
    }

    public ResponseEntity<?> getSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new ResourceNotFoundException("Subject does not exist");
        }
        Subject subject = subjectRepository.findById(subjectId).get();
        return ResponseEntity.ok(subject);
    }

    public ResponseEntity<List<QuestionResDto>> getSubjectQuestions(Long subjectId) {
        Subject subject =subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

        return ResponseEntity.ok(subject.getQuestionList().stream()
                .map(question -> modelMapper.map(question, QuestionResDto.class))
                .toList());
    }

}
