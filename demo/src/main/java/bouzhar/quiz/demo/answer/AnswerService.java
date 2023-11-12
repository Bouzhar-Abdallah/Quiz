package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    public List<Answer> getAnswers(){
        return answerRepository.findAll();
    }

    public ResponseEntity<Answer> addAnswer(Answer answer) {
        if (existsAnswerByAnswer(answer.getAnswer())) {
            throw new IllegalStateException("Answer with the same text already exists");
        }
        Answer createdAnswer = answerRepository.save(answer);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }

    private boolean existsAnswerByAnswer(String answer) {
        return answerRepository.existsByAnswer(answer);
    }

    public Answer findAnswerById(long l) {
        return answerRepository.findById(l).orElseThrow(()-> new IllegalStateException(
                "answer with id: "+l+" does not exist"
        ));
    }

    public ResponseEntity<Answer> updateAnswer(Answer answer) {
        Answer existingAnswer = answerRepository.findById(answer.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "answer with id: "+answer.getId()+" does not exist"
                ));
        existingAnswer.setAnswer(answer.getAnswer());
        Answer updatedAnswer = answerRepository.save(existingAnswer);
        return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAnswer(Long answerId) {
        boolean exists = answerRepository.existsById(answerId);
        if(!exists){
            throw new ResourceNotFoundException(
                    "answer with id: "+answerId+" does not exist"
            );
        }
        answerRepository.deleteById(answerId);
        return new ResponseEntity<>("Answer with id: "+answerId+" deleted succesefully",HttpStatus.OK);
    }
}
