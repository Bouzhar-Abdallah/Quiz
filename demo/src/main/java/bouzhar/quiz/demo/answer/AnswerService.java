package bouzhar.quiz.demo.answer;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Answer addAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer findAnswerById(long l) {
        return answerRepository.findById(l).orElseThrow(()-> new IllegalStateException(
                "answer with id: "+l+" does not exist"
        ));
    }
}
