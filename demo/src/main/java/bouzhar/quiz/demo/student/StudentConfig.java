package bouzhar.quiz.demo.student;

import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.subject.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner studentCommandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student student = new Student();
            student.setFirstName("abdallah");
            student.setLastName("bouzhar");
            student.setEmail("abd@gmail.com");
            student.setAdress("sebt gzoula");
            student.setRegestrationDate(LocalDate.now());

            studentRepository.saveAll(List.of(student));
        };
    }
}
