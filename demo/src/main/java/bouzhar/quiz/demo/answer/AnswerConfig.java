package bouzhar.quiz.demo.answer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AnswerConfig {
    @Bean
    CommandLineRunner answerCommandLineRunner(AnswerRepository answerRepository){
        return args -> {
            Answer answerA = new Answer("Answer A");
            Answer answerB = new Answer("Answer B");
            Answer answerC = new Answer("Answer C");
            Answer answerD = new Answer("Answer D");
            answerRepository.saveAll(List.of(answerA, answerB, answerC, answerD));
        };
    }
}
