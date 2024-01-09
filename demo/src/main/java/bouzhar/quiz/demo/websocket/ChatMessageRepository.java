package bouzhar.quiz.demo.websocket;

import bouzhar.quiz.demo.websocket.entities.ChatMessage;
import org.aspectj.bridge.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
    @Query("SELECT m FROM ChatMessage m WHERE m.participate.participateId.room.id = :salleId order by m.timeStamp asc ")
    List<ChatMessage> findMessagesByRoomId(@Param("salleId") Long salleId);
}
