package bouzhar.quiz.demo.websocket;

import bouzhar.quiz.demo.chat.ChatService;
import bouzhar.quiz.demo.websocket.dto.MessageDto;
import bouzhar.quiz.demo.websocket.entities.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "api/v2")
public class WebSocketController {
    private final ChatService chatService;
    //private final SimpMessageSendingOperations messagingTemplate;


    public WebSocketController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage/{roomId}")
    @SendTo("/room/{roomId}")
    public MessageDto send(MessageDto message, @DestinationVariable int roomId){
        System.out.println("WS controller" + message.toString());
        return chatService.saveChatMessage(message.getParticipate().getStudent().getId(),message.getParticipate().getRoom().getId(),message.getContent());
    }
    @MessageMapping("/chat2.sendMessage/{salonId}")
    @SendTo("/topic/salon/{salonId}")
    public ChatMessage handleChatMessage(@DestinationVariable Long salonId, ChatMessage messageDTO) {
        chatService.saveChatMessage(messageDTO.getParticipate().getStudent().getId(), salonId, messageDTO.getContent());
        return messageDTO;
    }

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getParticipate().getStudent().getFirstName() );
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
