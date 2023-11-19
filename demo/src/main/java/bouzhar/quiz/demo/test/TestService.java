package bouzhar.quiz.demo.test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService implements TestServiceSpecification {
    private final TestRepository testRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TestService(TestRepository testRepository, ModelMapper modelMapper) {
        this.testRepository = testRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<TestDto> addNewTest(TestDto testDto) {
        return ResponseEntity.ok(
                modelMapper.map(testRepository.save(modelMapper.map(testDto, Test.class)),TestDto.class)
        );
    }

    @Override
    public ResponseEntity<TestDto> updateTest(TestDto testDto) {
        return null;
    }

    @Override
    public ResponseEntity<TestDto> deleteTest(Long Id) {
        return null;
    }

    @Override
    public ResponseEntity<TestDto> getTest(Long Id) {
        return null;
    }

    @Override
    public ResponseEntity<List<TestDto>> getAllTests() {
        return null;
    }
}
