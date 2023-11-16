package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService implements AnswerServiceI {
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public AnswerService(AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }
    /*
     *
     * Methods
     *
     * */
    @Override
    public ResponseEntity<List<AnswerDto>> getAnswers(){
        return ResponseEntity.ok(answerRepository.findAll().stream()
                .map(answer -> modelMapper.map(answer,AnswerDto.class))
                .toList()
        );
    }
    @Override
    public boolean existsAnswerByAnswer(String answer) {
        return answerRepository.existsByAnswer(answer);
    }
    @Override
    public ResponseEntity<AnswerDto> addAnswer(AnswerDto answerDto) {
        if (existsAnswerByAnswer(answerDto.getAnswer())) {
            throw new IllegalStateException("Answer with the same text already exists");
        }
        Answer createdAnswer = answerRepository.save(modelMapper.map(answerDto,Answer.class));
        return new ResponseEntity<>(modelMapper.map(createdAnswer,AnswerDto.class), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AnswerDto> updateAnswer(AnswerDto answerDto) {
        Answer existingAnswer = answerRepository.findById(answerDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "answer with id: "+answerDto.getId()+" does not exist"
                ));
        existingAnswer.setAnswer(answerDto.getAnswer());
        Answer updatedAnswer = answerRepository.save(existingAnswer);
        return new ResponseEntity<>(modelMapper.map(updatedAnswer,AnswerDto.class), HttpStatus.OK);
    }

    @Override
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
    @Override
    public ResponseEntity<AnswerDto> getAnswer(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(()-> new ResourceNotFoundException(
                "answer with id: "+answerId+" does not exist"
        ));
        return ResponseEntity.ok(modelMapper.map(answer,AnswerDto.class));
    }
}
