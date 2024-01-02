package bouzhar.quiz.demo.response;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Long> {
    @Query("SELECT COUNT(DISTINCT r.validation.question) FROM Response r WHERE r.assignment.id = :assignmentId")
    Long countDistinctQuestionsByAssignmentId(@Param("assignmentId") Long assignmentId);
}
