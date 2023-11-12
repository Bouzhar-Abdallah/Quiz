package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.level.LevelRepository;
import bouzhar.quiz.demo.level.dtos.LevelDto;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.subject.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final LevelRepository levelRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ModelMapper modelMapper, LevelRepository levelRepository, SubjectRepository subjectRepository) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
        this.levelRepository = levelRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public Question addQuestion(QuestionDto question) {
        /*if (!levelRepository.existsById(question.getLevelId()))throw new ResourceNotFoundException("level with id: "+question.getLevelId()+" does not exist" );
        if (!subjectRepository.existsById(question.getSubjectId()))throw new ResourceNotFoundException("subject with id: "+question.getSubjectId()+" does not exist" );*/
        Level level = levelRepository.findById(question.getLevel().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + question.getLevel().getId()));

        Subject subject = subjectRepository.findById(question.getSubject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + question.getSubject().getId()));

        try{
            Question toCreateQuestion = modelMapper.map(question,Question.class);
            questionRepository.save(toCreateQuestion);
            Optional<Question> createdQuestion = questionRepository.findById(toCreateQuestion.getId());
            System.out.println(createdQuestion.get().getLevel().toString());
            return createdQuestion.orElse(null);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Question();
        }

    }
}
