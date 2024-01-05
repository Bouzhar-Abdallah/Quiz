package bouzhar.quiz.demo.assignment;

import bouzhar.quiz.demo.question.dto.QuestionWithSimpleAnswers;
import bouzhar.quiz.demo.temporization.Temporization;
import bouzhar.quiz.demo.temporization.TemporizationResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAllByStudentId(Long id);

    List<Assignment> findAllByTestIdOrderByChanceAsc(Long TestId);

    @Query("SELECT t FROM Temporization t JOIN t.test test WHERE test.id = (SELECT a.test.id FROM Assignment a WHERE a.id = :assignmentId)")
    List<Temporization> findTemporizationsByAssignmentId(@Param("assignmentId") Long assignmentId);
}
