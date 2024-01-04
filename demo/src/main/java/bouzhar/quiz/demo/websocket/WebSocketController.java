package bouzhar.quiz.demo.websocket;

import bouzhar.quiz.demo.websocket.entities.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final WebSocketService chatService;
    //private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketController(WebSocketService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage/{salonId}")
    @SendTo("/topic/salon/{salonId}")
    public ChatMessage handleChatMessage(@DestinationVariable Long salonId, ChatMessage messageDTO) {
        chatService.saveChatMessage(messageDTO.getParticipate().getStudent().getId(), salonId, messageDTO.getContent());
        return messageDTO;
    }
}
