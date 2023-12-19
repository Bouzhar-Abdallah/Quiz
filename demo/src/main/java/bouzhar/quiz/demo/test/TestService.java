package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.answer.dto.AnswerResDto;
import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.QuestionRepository;
import bouzhar.quiz.demo.teacher.Teacher;
import bouzhar.quiz.demo.teacher.TeacherRepository;
import bouzhar.quiz.demo.temporization.Temporization;
import bouzhar.quiz.demo.temporization.TemporizationId;
import bouzhar.quiz.demo.temporization.TemporizationReqDto;
import bouzhar.quiz.demo.test.Dtos.TestReqDto;
import bouzhar.quiz.demo.test.Dtos.TestResDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestService implements TestServiceSpecification {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;


    /*
     *
     * Methods
     *
     * */

    // add new test
    @Override
    public TestResDto addNewTest(TestReqDto testReqDto) {
        Teacher teacher = teacherRepository.findById(testReqDto.getTeacher_id()).orElseThrow(
                () -> new ResourceNotFoundException("teacher with id " + testReqDto.getTeacher_id() + " not found")
        );
        Test test = modelMapper.map(testReqDto, Test.class);
        test.setTeacher(teacher);
        return
                modelMapper.map(testRepository.save(test), TestResDto.class);
    }

    // get test by id
    @Override
    public TestResDto getTest(Long id) {
        Test test = testRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + id + " not found")
        );
        return modelMapper.map(test, TestResDto.class);
    }

    // get all tests
    @Override
    public List<TestResDto> getAllTests() {
        return
                testRepository.findAll().stream()
                        .map(test -> modelMapper.map(test, TestResDto.class))
                        .toList();
    }

    // update test
    @Override
    public TestResDto updateTest(TestReqDto testReqDto) {
        testRepository.findById(testReqDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + testReqDto.getId() + " not found")
        );
        Teacher teacher = teacherRepository.findById(testReqDto.getTeacher_id()).orElseThrow(
                () -> new ResourceNotFoundException("teacher with id " + testReqDto.getTeacher_id() + " not found")
        );
        Test testToUpdate = modelMapper.map(testReqDto, Test.class);
        testToUpdate.setTeacher(teacher);
        return
                modelMapper.map(
                        testRepository.save(testToUpdate)
                        , TestResDto.class);
    }

    // delete test
    @Override
    public TestResDto deleteTest(Long id) {
        Test test = testRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + id + " not found")
        );
        testRepository.deleteById(id);
        return
                modelMapper.map(test, TestResDto.class);
    }

    // add question to test
    @Override
    public TestResDto addQuestion(TemporizationReqDto temporizationReqDto) {
        Test test = testRepository.findById(temporizationReqDto.getTest_id()).orElseThrow(
                () -> new ResourceNotFoundException("test with id " + temporizationReqDto.getTest_id() + " not found")
        );
        Question question = questionRepository.findById(temporizationReqDto.getQuestion_id()).orElseThrow(
                () -> new ResourceNotFoundException("question with id " + temporizationReqDto.getQuestion_id() + " not found")
        );
        Temporization temporization = new Temporization();
        temporization.setTemporizationId(new TemporizationId(question.getId(), test.getId()));
        temporization.setQuestion(question);
        temporization.setTest(test);
        temporization.setDuration(temporizationReqDto.getDuration());
        test.getTemporizations().add(temporization);
        testRepository.save(test);
        return
                modelMapper.map(test, TestResDto.class);
    }

    @Override
    public Page<TestResDto> getPaginatedAnswers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return testRepository.findAll(pageRequest).map(test -> modelMapper.map(test, TestResDto.class));
    }
}
