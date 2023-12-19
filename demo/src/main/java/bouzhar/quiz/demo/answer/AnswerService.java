package bouzhar.quiz.demo.answer;

import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.answer.dto.AnswerSimpleDto;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    // create answer
    @Override
    public AnswerSimpleDto addAnswer(AnswerSimpleDto answerSimpleDto) {
        if (existsAnswerByAnswer(answerSimpleDto.getAnswer())) {
            throw new IllegalStateException("Answer with the same text already exists");
        }
        Answer createdAnswer = answerRepository.save(modelMapper.map(answerSimpleDto,Answer.class));
        return modelMapper.map(createdAnswer, AnswerSimpleDto.class);
    }

    // get answer by id
    @Override
    public AnswerResDto getAnswer(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(()-> new ResourceNotFoundException(
                "answer with id: "+answerId+" does not exist"
        ));
        return modelMapper.map(answer, AnswerResDto.class);
    }

    // get all answers
    @Override
    public List<AnswerResDto> getAnswers(){
        return answerRepository.findAll().stream()
                .map(answer -> modelMapper.map(answer, AnswerResDto.class))
                .toList();
    }

    // update answer
    @Override
    public AnswerResDto updateAnswer(AnswerSimpleDto answerSimpleDto) {
        Answer existingAnswer = answerRepository.findById(answerSimpleDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "answer with id: "+ answerSimpleDto.getId()+" does not exist"
                ));
        existingAnswer.setAnswer(answerSimpleDto.getAnswer());
        Answer updatedAnswer = answerRepository.save(existingAnswer);
        return modelMapper.map(updatedAnswer, AnswerResDto.class);
    }

    // delete answer
    @Override
    public AnswerResDto deleteAnswer(Long answerId) {
        Answer existingAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "answer with id: "+ answerId+" does not exist"
                ));
        answerRepository.deleteById(answerId);
        return modelMapper.map(existingAnswer,AnswerResDto.class);
    }

    @Override
    public Page<AnswerResDto> getPaginatedAnswers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return answerRepository.findAll(pageRequest).map(answer -> modelMapper.map(answer, AnswerResDto.class));
    }

    /*
    *
    * helper methods
    *
    *  */

    // check if answer exists with same text
    @Override
    public boolean existsAnswerByAnswer(String answer) {
        return answerRepository.existsByAnswer(answer);
    }



}
