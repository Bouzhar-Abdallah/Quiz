package bouzhar.quiz.demo.validation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation,ValidationId> {
}
