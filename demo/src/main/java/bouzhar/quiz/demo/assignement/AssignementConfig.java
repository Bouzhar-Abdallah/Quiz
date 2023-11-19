package bouzhar.quiz.demo.assignement;

import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.test.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class AssignementConfig {
    @Bean
    CommandLineRunner assignementCommandLineRunner(AssignementRepository assignementRepository){
        return args -> {
            Assignement assignement = new Assignement();
            assignement.setChance(1);
            assignement.setResult(false);
            assignement.setObtainedScore(20F);
            assignement.setStartDate(
                    LocalDateTime.now()
            );
            assignement.setEndDate(
                    LocalDateTime.now().minusDays(1)
            );

            assignementRepository.saveAll(List.of(assignement));
        };
    }
}
