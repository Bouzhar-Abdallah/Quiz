package bouzhar.quiz.demo.subject;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.Question;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public ResponseEntity<Subject> addSubject(Subject subject) {
        if (existsSubjectByName(subject.getName())) {
            throw new ValidationException("Subject with the same name already exists");
        }
        if (subject.getParent() != null) {
            if (!subjectRepository.existsById(subject.getParent().getId())) {
                throw new ResourceNotFoundException("Parent subject does not exist");
            }
        }
        Subject createdSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(createdSubject);
    }

    private boolean existsSubjectByName(String name) {
        return subjectRepository.existsByName(name);
    }

    public ResponseEntity<Subject> updateSubject(Subject subject) {
        if (!subjectRepository.existsById(subject.getId())) {
            throw new ResourceNotFoundException("Subject does not exist");
        }
        if (subject.getParent() != null) {
            if (!subjectRepository.existsById(subject.getParent().getId())) {
                throw new ResourceNotFoundException("Parent subject does not exist");
            }
        }
        Subject updatedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(updatedSubject);
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

    public ResponseEntity<List<Question>> getSubjectQuestions(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new ResourceNotFoundException("Subject does not exist");
        }
        Subject subject = subjectRepository.findById(subjectId).get();
        return ResponseEntity.ok(subject.getQuestionList());
    }
}
