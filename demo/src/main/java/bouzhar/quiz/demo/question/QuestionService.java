package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.level.LevelRepository;
import bouzhar.quiz.demo.media.Media;
import bouzhar.quiz.demo.media.MediaDto;
import bouzhar.quiz.demo.media.MediaService;
import bouzhar.quiz.demo.question.dto.QuestionReqDto;
import bouzhar.quiz.demo.question.dto.QuestionResDto;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.subject.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public List<QuestionResDto> getQuestions() {
        List<Question> questions =questionRepository.findAll();
        return Arrays.asList(modelMapper.map(questions, QuestionResDto[].class));
    }


    public QuestionResDto addQuestion(QuestionReqDto questionReqDto) {
        Level level = levelRepository.findById(questionReqDto.getLevel_id())
                .orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + questionReqDto.getLevel_id()));

        Subject subject = subjectRepository.findById(questionReqDto.getSubject_id())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + questionReqDto.getSubject_id()));
        Question question = modelMapper.map(questionReqDto, Question.class);
        question.setLevel(level);
        question.setSubject(subject);
        for (Media mediaDto : questionReqDto.getMedias()) {
            mediaDto.setQuestion(question);
        }
        Question savedQuestion = questionRepository.save(question);
        //Question savedQuestion = questionRepository.save(modelMapper.map(questionReqDto, Question.class));

        return modelMapper.map(questionRepository.findById(savedQuestion.getId()).orElseThrow(() -> new ResourceNotFoundException("Operation Failed")), QuestionResDto.class);
    }


    public ResponseEntity<QuestionResDto> updateQuestion(Long id, QuestionReqDto questionReqDto) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The question with ID " + id + " does not exist"));
        Level level = levelRepository.findById(questionReqDto.getLevel_id())
                .orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + questionReqDto.getLevel_id()));

        Subject subject = subjectRepository.findById(questionReqDto.getSubject_id())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + questionReqDto.getSubject_id()));

        existingQuestion.setText(questionReqDto.getText());
        existingQuestion.setAnswersCount(questionReqDto.getAnswersCount());
        existingQuestion.setCorrectAnsqersCount(questionReqDto.getCorrectAnsqersCount());
        existingQuestion.setScorePoints(questionReqDto.getScorePoints());
        existingQuestion.setType(questionReqDto.getType());
        existingQuestion.setSubject(subject);
        existingQuestion.setLevel(level);


        Question updatedQuestion = questionRepository.save(existingQuestion);
        return ResponseEntity.ok(modelMapper.map(updatedQuestion, QuestionResDto.class));
    }

    public ResponseEntity<QuestionResDto> findById(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("question not found"));
        return ResponseEntity.ok(modelMapper.map(question, QuestionResDto.class));
    }

    public ResponseEntity<String> deleteQuestion(Long questionId) {
        questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("question not found"));
        questionRepository.deleteById(questionId);
        return ResponseEntity.ok("Question with id: " + questionId + " deleted succesefully");
    }

}
