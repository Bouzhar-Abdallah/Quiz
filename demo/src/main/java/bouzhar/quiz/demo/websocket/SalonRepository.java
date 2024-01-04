package bouzhar.quiz.demo.websocket;

import bouzhar.quiz.demo.websocket.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<Room, Long> {
}
