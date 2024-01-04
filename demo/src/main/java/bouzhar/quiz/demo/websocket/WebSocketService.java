package bouzhar.quiz.demo.websocket;

import bouzhar.quiz.demo.websocket.entities.ChatMessage;
import bouzhar.quiz.demo.websocket.entities.Participate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WebSocketService {
    private final ChatMessageRepository chatMessageRepository;
    private final ModelMapper modelMapper;

    private final ParticipateRepository participateRepository;




    public void saveChatMessage(Long studentId, Long salonId, String content) {
        try {
            Optional<Participate> participateOptional = participateRepository.findByStudentIdAndSalonId(studentId, salonId);
            if (participateOptional.isPresent()) {
                Participate participate = participateOptional.get();
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setContent(content);
                chatMessage.setParticipate(participate);
                chatMessage.setTimestamp(LocalDateTime.now());
                chatMessageRepository.save(chatMessage);
            } else {
                throw new RuntimeException("Participate not found for studentId: " + studentId + " and salonId: " + salonId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
