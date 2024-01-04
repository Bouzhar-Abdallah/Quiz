package bouzhar.quiz.demo.response;

import bouzhar.quiz.demo.response.dto.ResponseReqDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/response")
public class ResponseController {
    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping
    public ResponseReqDto addResponse(@RequestBody @Valid ResponseReqDto responseReqDto){
        return responseService.create(responseReqDto);
    }
    @PostMapping(path = "all")
    public void addManyResponse(@RequestBody List<ResponseReqDto> responses){
        responses.forEach(responseService::create);
    }
}
