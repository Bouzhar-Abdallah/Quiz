package bouzhar.quiz.demo.assignment;

import bouzhar.quiz.demo.question.dto.QuestionWithSimpleAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
    List<Assignment> findAllByStudentId(Long id);
    List<Assignment> findAllByTestIdOrderByChanceAsc(Long TestId);
}
