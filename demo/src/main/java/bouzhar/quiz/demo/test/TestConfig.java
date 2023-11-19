package bouzhar.quiz.demo.test;

import bouzhar.quiz.demo.teacher.Teacher;
import bouzhar.quiz.demo.teacher.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class TestConfig {
    @Bean
    CommandLineRunner testCommandLineRunner(TestRepository testRepository){
        return args -> {
            Test test = new Test();
            test.setDuration(90);
            test.setShowResps(false);
            test.setShowResults(false);
            test.setSuccessScore(30F);
            test.setMaxAttempts(3);
            test.setRemarks("remarks");
            test.setInstructions("instructions");

            testRepository.saveAll(List.of(test));
        };
    }
}
