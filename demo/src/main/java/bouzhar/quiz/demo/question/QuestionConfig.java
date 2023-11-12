package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.answer.AnswerRepository;
import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.level.LevelRepository;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.subject.SubjectRepository;
import org.springframework.boot.CommandLineRunner;

import java.math.BigInteger;

//@Configuration
public class QuestionConfig {
    //@Bean
    CommandLineRunner commandLineRunner2(QuestionRepository questionRepository, AnswerRepository answerRepository, LevelRepository levelRepository,SubjectRepository subjectRepository){
        return args -> {

            Question question1 = new Question(
                    4,
                    1,
                    "first question",
                    BigInteger.valueOf(200),
                    0F,
                    QuestionType.MultipleChoice
            );
            Question question2 = new Question(
                    4,
                    2,
                    "second question",
                    BigInteger.valueOf(200),
                    0F,
                    QuestionType.MultipleChoice
            );
            Level level = levelRepository.findById(1L).get();
            Subject subject = subjectRepository.findById(1L).get();
            question2.setLevel(level);
            question2.setSubject(subject);
            question1.setLevel(level);
            question1.setSubject(subject);
            questionRepository.save(question1);
            questionRepository.save(question2);
        };
    }
}
