package bouzhar.quiz.demo.temporization;

import java.util.List;

public interface TemporizationServiceSpecification {
    TemporizationResDto addNewTemporization(TemporizationReqDto temporizationReqDto);
    TemporizationResDto deleteTemporization(TemporizationId temporizationId);

    List<TemporizationResDto> getAll();
}
