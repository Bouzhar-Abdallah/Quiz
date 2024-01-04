package bouzhar.quiz.demo.websocket;

import bouzhar.quiz.demo.websocket.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
}
