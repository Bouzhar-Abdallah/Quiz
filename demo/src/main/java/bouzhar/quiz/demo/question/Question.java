package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.validation.Validation;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@RequiredArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Question implements Serializable{


    @Id
/*    @SequenceGenerator(
            name ="question_sequence",
            sequenceName ="question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull private Integer answersCount;
    @NonNull private Integer correctAnsqersCount;
    @NonNull private String text;
    @NonNull private BigInteger duration;
    @NonNull private Float scorePoints;
    @Enumerated(EnumType.STRING)
    @NonNull private QuestionType type;

    public void setLevel(Level level) {
        this.level = level;
    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Validation> validations;


    @ManyToOne()
    @JoinColumn(name = "level_id")
    private Level level;


    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public QuestionDto toQuestionDto() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(this.id);
        questionDto.setAnswersCount(this.answersCount);
        questionDto.setCorrectAnsqersCount(this.correctAnsqersCount);
        questionDto.setText(this.text);
        questionDto.setDuration(this.duration);
        questionDto.setScorePoints(this.scorePoints);
        questionDto.setType(this.type);
        questionDto.setLevel(this.level);
        questionDto.setSubject(this.subject);
        return questionDto;
    }
}
