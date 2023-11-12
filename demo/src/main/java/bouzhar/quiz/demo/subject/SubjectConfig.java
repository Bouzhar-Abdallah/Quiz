package bouzhar.quiz.demo.subject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubjectConfig {
    @Bean
    CommandLineRunner subjectCommandLineRunner(SubjectRepository subjectRepository){
        return args -> {
            Subject subject1 = new Subject("Programming");
            Subject subject2 = new Subject("java");
            subject2.setParent(subject1);
            subjectRepository.saveAll(List.of(subject1,subject2));
        };
    }
}
