package bouzhar.quiz.demo.test;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TestServiceSpecification {
    ResponseEntity<TestDto> addNewTest(TestDto testDto);

    ResponseEntity<TestDto> updateTest(TestDto testDto);

    ResponseEntity<TestDto> deleteTest(Long Id);

    ResponseEntity<TestDto> getTest(Long Id);
    ResponseEntity<List<TestDto>> getAllTests();
}
