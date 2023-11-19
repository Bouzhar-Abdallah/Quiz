package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
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
    public ResponseEntity<TestDto> getTest(Long id) {
        Test test = testRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("test with id "+ id +" not found")
        );
        return ResponseEntity.ok(
                modelMapper.map(test,TestDto.class)
        );
    }
    @Override
    public ResponseEntity<List<TestDto>> getAllTests() {
        return ResponseEntity.ok(
                testRepository.findAll().stream()
                        .map(test -> modelMapper.map(test,TestDto.class))
                        .toList()
        );
    }
    @Override
    public ResponseEntity<TestDto> updateTest(TestDto testDto) {
        testRepository.findById(testDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("test with id "+ testDto.getId() +" not found")
        );
        return ResponseEntity.ok(
                modelMapper.map(
                        testRepository.save(modelMapper.map(testDto,Test.class))
                        ,TestDto.class)
        );
    }

    @Override
    public ResponseEntity<TestDto> deleteTest(Long Id) {
        return null;
    }


}
