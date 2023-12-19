package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.answer.dto.AnswerResDto;
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
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService implements QuestionServiceSpecification {
    private final QuestionRepository questionRepository;
    private final LevelRepository levelRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;


    /*
    *
    * Methods
    *
    * */

    // Add a question
    @Override
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

        return modelMapper.map(savedQuestion, QuestionResDto.class);
    }
    // Get a question by id
    @Override
    public QuestionResDto getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("question not found"));
        return modelMapper.map(question, QuestionResDto.class);
    }
    // Get all questions
    @Override
    public List<QuestionResDto> getQuestions() {
        return questionRepository.findAll().stream().
                map(question -> modelMapper.map(question, QuestionResDto.class)).
                toList();
    }
    // Update a question
    @Override
    public QuestionResDto updateQuestion(Long id, QuestionReqDto questionReqDto) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The question with ID " + id + " does not exist"));
        Level level = levelRepository.findById(questionReqDto.getLevel_id())
                .orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + questionReqDto.getLevel_id()));

        Subject subject = subjectRepository.findById(questionReqDto.getSubject_id())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + questionReqDto.getSubject_id()));

        existingQuestion.setText(questionReqDto.getText());
        existingQuestion.setAnswersCount(questionReqDto.getAnswersCount());
        existingQuestion.setCorrectAnswersCount(questionReqDto.getCorrectAnswersCount());
        existingQuestion.setType(questionReqDto.getType());
        existingQuestion.setSubject(subject);
        existingQuestion.setLevel(level);


        Question updatedQuestion = questionRepository.save(existingQuestion);
        return modelMapper.map(updatedQuestion, QuestionResDto.class);
    }
    // Delete a question
    @Override
    public QuestionResDto deleteQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("question not found"));
        questionRepository.deleteById(questionId);
        return modelMapper.map(question, QuestionResDto.class);
    }

    @Override
    public Page<QuestionResDto> getPaginatedAnswers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return questionRepository.findAll(pageRequest).map(question -> modelMapper.map(question, QuestionResDto.class));
    }
}
