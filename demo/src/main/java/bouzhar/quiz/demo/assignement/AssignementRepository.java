package bouzhar.quiz.demo.assignement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignementRepository extends JpaRepository<Assignement,Long> {
}
