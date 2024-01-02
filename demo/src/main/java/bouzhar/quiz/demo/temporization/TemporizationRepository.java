package bouzhar.quiz.demo.temporization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporizationRepository extends JpaRepository<Temporization,TemporizationId> {
}
