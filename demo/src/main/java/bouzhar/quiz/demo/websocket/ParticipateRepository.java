package bouzhar.quiz.demo.websocket;

import bouzhar.quiz.demo.websocket.entities.Participate;
import bouzhar.quiz.demo.websocket.entities.ParticipateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, ParticipateId> {
    @Query("SELECT p FROM Participate p WHERE p.participateId.student.id = :studentId AND p.participateId.room.id = :salonId")
    Optional<Participate> findByStudentIdAndSalonId(Long studentId, Long salonId);
}
