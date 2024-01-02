package bouzhar.quiz.demo.temporization;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/temporization")
public class TemporizationController {
    public TemporizationController(TemporizationService temporizationService) {
        this.temporizationService = temporizationService;
    }
    private final TemporizationService temporizationService;

    @PostMapping()
    public TemporizationResDto addNew(@RequestBody TemporizationReqDto temporizationReqDto){
        return this.temporizationService.addNewTemporization(temporizationReqDto);
    }

    @GetMapping()
    public List<TemporizationResDto> getAll(){
        return this.temporizationService.getAll();
    }
}
