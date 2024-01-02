package bouzhar.quiz.demo.temporization;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.QuestionRepository;
import bouzhar.quiz.demo.test.Test;
import bouzhar.quiz.demo.test.TestRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TemporizationService implements TemporizationServiceSpecification{
    private final TemporizationRepository temporizationRepository;

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<TemporizationResDto> getAll() {
        return temporizationRepository.findAll().stream().map(
                temporization -> modelMapper.map(temporization,TemporizationResDto.class)
        ).toList();
    }
    @Override
    public TemporizationResDto addNewTemporization(TemporizationReqDto temporizationReqDto) {
        var test = testRepository.findById(temporizationReqDto.getTest_id()).orElseThrow(
                ()-> new ResourceNotFoundException("quiz non trouvé")
        );
        var question = questionRepository.findById(temporizationReqDto.getQuestion_id()).orElseThrow(
                () -> new ResourceNotFoundException("question non trouvé")
        );
        Temporization temporization = new Temporization(new TemporizationId(question.getId(), test.getId()),temporizationReqDto.getDuration());
        temporization.setQuestion(question);
        temporization.setTest(test);
        question.setTemporization(temporization);

        return modelMapper.map(questionRepository.save(question),TemporizationResDto.class);
    }

    @Override
    public TemporizationResDto deleteTemporization(TemporizationId temporizationId) {
        return null;
    }
}
