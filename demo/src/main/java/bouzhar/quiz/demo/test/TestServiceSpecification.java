package bouzhar.quiz.demo.test;


import bouzhar.quiz.demo.temporization.TemporizationReqDto;
import bouzhar.quiz.demo.test.Dtos.TestReqDto;
import bouzhar.quiz.demo.test.Dtos.TestResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TestServiceSpecification {
    ResponseEntity<TestResDto> addNewTest(TestReqDto testResDto);

    ResponseEntity<TestResDto> updateTest(TestReqDto testResDto);

    ResponseEntity<TestResDto> deleteTest(Long Id);

    ResponseEntity<TestResDto> getTest(Long Id);
    ResponseEntity<List<TestResDto>> getAllTests();

    ResponseEntity<TestResDto> addQuestion(TemporizationReqDto temporizationReqDto);
}
