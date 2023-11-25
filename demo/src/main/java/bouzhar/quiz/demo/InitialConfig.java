package bouzhar.quiz.demo;

import bouzhar.quiz.demo.answer.Answer;
import bouzhar.quiz.demo.answer.AnswerRepository;
import bouzhar.quiz.demo.assignment.Assignment;
import bouzhar.quiz.demo.assignment.AssignmentRepository;
import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.level.LevelRepository;
import bouzhar.quiz.demo.question.QuestionRepository;
import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.student.StudentRepository;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.subject.SubjectRepository;
import bouzhar.quiz.demo.teacher.Teacher;
import bouzhar.quiz.demo.teacher.TeacherRepository;
import bouzhar.quiz.demo.test.Test;
import bouzhar.quiz.demo.test.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class InitialConfig {
    @Bean
    CommandLineRunner CommandLineRunner(
            AnswerRepository answerRepository,
            QuestionRepository questionRepository,
            AssignmentRepository assignmentRepository,
            LevelRepository levelRepository,
            SubjectRepository subjectRepository,
            StudentRepository studentRepository,
            TestRepository testRepository,
            TeacherRepository teacherRepository
            ){
        return args -> {
            Answer answerA = new Answer("Answer A");
            Answer answerB = new Answer("Answer B");
            Answer answerC = new Answer("Answer C");
            Answer answerD = new Answer("Answer D");
            answerRepository.saveAll(List.of(answerA, answerB, answerC, answerD));
            /*****/
            Subject subject1 = new Subject("math");
            Subject subject2 = new Subject("physique");
            subject2.setParent(subject1);
            subjectRepository.saveAll(List.of(subject1,subject2));
            /*****/
            Student student = new Student();
            student.setFirstName("abdallah");
            student.setLastName("bouzhar");
            student.setEmail("abd@gmail.com");
            student.setAdress("sebt gzoula");
            student.setRegistrationDate(LocalDate.now());
            studentRepository.saveAll(List.of(student));
            /******/
            Level level1 = new Level(
                    "niveau 1",
                    4F,
                    2F
            );
            Level level2 = new Level(
                    "niveau 2",
                    8F,
                    4F
            );
            levelRepository.saveAll(List.of(level1,level2));
            /******/
            Teacher teacher = new Teacher();
            teacher.setFirstName("abdallah");
            teacher.setLastName("bouzhar");
            teacher.setEmail("abd@gmail.com");
            teacher.setAdress("sebt gzoula");
            teacher.setSpeciality("math");
            teacher.setRegistrationDate(LocalDate.now());

            teacherRepository.saveAll(List.of(teacher));
            /******/
            Test test = new Test();
            test.setDuration(90);
            test.setShowResps(false);
            test.setShowResults(false);
            test.setTitle("title");
            test.setSuccessScore(30F);
            test.setMaxAttempts(3);
            test.setRemarks("remarks");
            test.setInstructions("instructions");

            testRepository.saveAll(List.of(test));
            /*****/
            Assignment assignment = new Assignment();
            assignment.setChance(1);
            assignment.setResult(false);
            assignment.setObtainedScore(20F);
            assignment.setStartDate(
                    LocalDateTime.now()
            );
            assignment.setEndDate(
                    LocalDateTime.now().minusDays(1)
            );

            assignmentRepository.saveAll(List.of(assignment));
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
