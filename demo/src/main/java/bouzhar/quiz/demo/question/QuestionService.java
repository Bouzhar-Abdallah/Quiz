package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.level.LevelRepository;
import bouzhar.quiz.demo.media.Media;
import bouzhar.quiz.demo.media.MediaDto;
import bouzhar.quiz.demo.media.MediaRepository;
import bouzhar.quiz.demo.media.MediaService;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.subject.SubjectRepository;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.digester.ArrayStack;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final LevelRepository levelRepository;
    private final SubjectRepository subjectRepository;
    private final MediaService mediaService;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, LevelRepository levelRepository, SubjectRepository subjectRepository, MediaService mediaService, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.levelRepository = levelRepository;
        this.subjectRepository = subjectRepository;
        this.mediaService = mediaService;
        this.modelMapper = modelMapper;
    }

    public List<QuestionDto> getQuestions() {
        List<Question> questions =questionRepository.findAll();
        return Arrays.asList(modelMapper.map(questions, QuestionDto[].class));
    }


    public QuestionDto addQuestion(QuestionDto questionDto) {
        levelRepository.findById(questionDto.getLevel().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + questionDto.getLevel().getId()));

        subjectRepository.findById(questionDto.getSubject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + questionDto.getSubject().getId()));

        for (MediaDto mediaDto : questionDto.getMedias()) {
            mediaDto.setQuestion(questionDto);
        }
        Question savedQuestion = questionRepository.save(modelMapper.map(questionDto, Question.class));

        return modelMapper.map(questionRepository.findById(savedQuestion.getId()).orElseThrow(() -> new ResourceNotFoundException("Operation Failed")),QuestionDto.class);
    }


    public ResponseEntity<QuestionDto> updateQuestion(Long id, QuestionDto questionDto) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The question with ID " + id + " does not exist"));
        levelRepository.findById(questionDto.getLevel().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + questionDto.getLevel().getId()));
        subjectRepository.findById(questionDto.getSubject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + questionDto.getSubject().getId()));

        existingQuestion.setDuration(questionDto.getDuration());
        existingQuestion.setText(questionDto.getText());
        existingQuestion.setAnswersCount(questionDto.getAnswersCount());
        existingQuestion.setCorrectAnsqersCount(questionDto.getCorrectAnsqersCount());
        existingQuestion.setScorePoints(questionDto.getScorePoints());
        existingQuestion.setType(questionDto.getType());
        existingQuestion.setSubject(questionDto.getSubject());
        existingQuestion.setLevel(questionDto.getLevel());


        Question updatedQuestion = questionRepository.save(existingQuestion);
        return ResponseEntity.ok(modelMapper.map(updatedQuestion,QuestionDto.class));
    }

    public ResponseEntity<QuestionDto> findById(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("question not found"));
        return ResponseEntity.ok(modelMapper.map(question,QuestionDto.class));
    }

    public ResponseEntity<String> deleteQuestion(Long questionId) {
        questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("question not found"));
        questionRepository.deleteById(questionId);
        return ResponseEntity.ok("Question with id: " + questionId + " deleted succesefully");
    }

}
