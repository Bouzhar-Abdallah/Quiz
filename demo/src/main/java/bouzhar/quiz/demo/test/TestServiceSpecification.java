package bouzhar.quiz.demo.test;


import bouzhar.quiz.demo.temporization.TemporizationReqDto;
import bouzhar.quiz.demo.test.Dtos.TestReqDto;
import bouzhar.quiz.demo.test.Dtos.TestResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TestServiceSpecification {
    TestResDto addNewTest(TestReqDto testResDto);
    TestResDto getTest(Long Id);
    List<TestResDto> getAllTests();
    TestResDto updateTest(TestReqDto testResDto);
    TestResDto deleteTest(Long Id);
    TestResDto addQuestion(TemporizationReqDto temporizationReqDto);
}
