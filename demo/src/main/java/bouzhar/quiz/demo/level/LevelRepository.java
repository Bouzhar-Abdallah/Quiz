package bouzhar.quiz.demo.level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level,Long> {
    boolean existsById(Long id);

    boolean existsByDescription(String description);
}
