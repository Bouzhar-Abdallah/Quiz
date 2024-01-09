package bouzhar.quiz.demo.chat;

import bouzhar.quiz.demo.websocket.dto.MessageDto;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v2/Message")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
/*

    @GetMapping
    public ResponseEntity<List<MessageDto>> all(){
        return new ResponseEntity<>(chatService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody MessageDTOreq messageDTOreq){
        return new ResponseEntity<>(chatService.save(messageDTOreq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MessageDto> update(@Valid @RequestBody MessageDTOreq messageDTOreq){
        return new ResponseEntity<>(chatService.save(messageDTOreq), HttpStatus.OK);
    }

    @GetMapping(path = {"{messageId}"})
    public ResponseEntity<MessageDto> findById(@PathVariable("messageId") Integer messageId){
        return new ResponseEntity<>(chatService.findById(messageId), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{messageId}"})
    public ResponseEntity<MessageDto> deleteById(@PathVariable("messageId") Integer messageId){
        return new ResponseEntity<>(chatService.deleteById(messageId), HttpStatus.OK);
    }*/

    @GetMapping(path = {"byRoom/{salleId}"})
    public List<MessageDto> findParticipatesByStudentId(@PathVariable("salleId") Long salleId){
        return this.chatService.findMessagesByRoomId(salleId);
    }
}
