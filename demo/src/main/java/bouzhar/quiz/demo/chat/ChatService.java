package bouzhar.quiz.demo.chat;

import bouzhar.quiz.demo.exception.ResourceNotFoundException;
import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.student.StudentRepository;
import bouzhar.quiz.demo.websocket.ChatMessageRepository;
import bouzhar.quiz.demo.websocket.ParticipateRepository;
import bouzhar.quiz.demo.websocket.RoomRepository;
import bouzhar.quiz.demo.websocket.dto.MessageDto;
import bouzhar.quiz.demo.websocket.entities.ChatMessage;
import bouzhar.quiz.demo.websocket.entities.Participate;
import bouzhar.quiz.demo.websocket.entities.ParticipateId;
import bouzhar.quiz.demo.websocket.entities.Room;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatService {
    private final ChatMessageRepository chatRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final ParticipateRepository participateRepository;
    private final StudentRepository studentRepository;


    public List<MessageDto> findMessagesByRoomId(Long salleId) {

        return chatRepository.findMessagesByRoomId(salleId).stream().map(
                message -> modelMapper.map(message, MessageDto.class)
        ).toList();
    }

    public MessageDto saveChatMessage(Long studentId, Long salonId, String content) {

        Optional<Participate> participateOptional = participateRepository.findByStudentIdAndSalonId(studentId, salonId);
        if (participateOptional.isPresent()) {
            Participate participate = participateOptional.get();
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent(content);
            chatMessage.setParticipate(participate);
            chatMessage.setTimeStamp(LocalDateTime.now());

            return modelMapper.map(chatRepository.save(chatMessage), MessageDto.class);
        } else {
            throw new RuntimeException("Participate not found for studentId: " + studentId + " and salonId: " + salonId);
        }

    }


    public MessageDto save(MessageDto messageDTOreq) {
        ChatMessage message = modelMapper.map(messageDTOreq, ChatMessage.class);
        Room room = roomRepository.findById(messageDTOreq.getParticipate().getRoom().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id room: " + messageDTOreq.getParticipate().getRoom().getId()));
        Student student = studentRepository.findById(messageDTOreq.getParticipate().getStudent().getId())
                .orElseThrow(() -> new ResourceNotFoundException("code student : " + messageDTOreq.getParticipate().getStudent().getId()));
        ParticipateId pId = new ParticipateId();
        pId.setRoom(room);
        pId.setStudent(student);
        Participate participate = participateRepository.findById(pId)
                .orElseThrow(() -> new ResourceNotFoundException("id participate : " + messageDTOreq.getParticipate().getStudent().getId()));
        message.setParticipate(participate);
        chatRepository.save(message);
        return modelMapper.map(message, MessageDto.class);
    }

}
