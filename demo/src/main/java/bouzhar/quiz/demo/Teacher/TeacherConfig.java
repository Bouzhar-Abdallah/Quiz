package bouzhar.quiz.demo.Teacher;

import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class TeacherConfig {
    @Bean
    CommandLineRunner teacherCommandLineRunner(TeacherRepository teacherRepository){
        return args -> {
            Teacher teacher = new Teacher();
            teacher.setFirstName("abdallah");
            teacher.setLastName("bouzhar");
            teacher.setEmail("abd@gmail.com");
            teacher.setAdress("sebt gzoula");
            teacher.setSpeciality("math");
            teacher.setRegestrationDate(LocalDate.now());

            teacherRepository.saveAll(List.of(teacher));
        };
    }
}
