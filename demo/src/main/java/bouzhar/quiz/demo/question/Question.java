package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.media.Media;
import bouzhar.quiz.demo.question.enums.QuestionType;
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

public class Question implements Serializable{


    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull private Integer answersCount;
    @NonNull private Integer correctAnsqersCount;
    @NonNull private String text;
    @NonNull private BigInteger duration;
    @NonNull private Float scorePoints;
    @Enumerated(EnumType.STRING)
    @NonNull private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Validation> validations;
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Media> medias;

    @ManyToOne()
    @JoinColumn(name = "level_id")
    private Level level;


    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
