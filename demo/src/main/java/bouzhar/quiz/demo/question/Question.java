package bouzhar.quiz.demo.question;

import bouzhar.quiz.demo.level.Level;
import bouzhar.quiz.demo.media.Media;
import bouzhar.quiz.demo.question.enums.QuestionType;
import bouzhar.quiz.demo.subject.Subject;
import bouzhar.quiz.demo.temporization.Temporization;
import bouzhar.quiz.demo.validation.Validation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@RequiredArgsConstructor


public class Question implements Serializable {


    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Transient
    private Integer answersCount;

    @Transient
    private Integer correctAnswersCount;



    @NonNull
    @Transient
    private Float scorePoints;


    @NonNull
    @NotBlank(message = "question text is mandatory")
    private String text;


    @Enumerated(EnumType.STRING)
    @NonNull
    @NotBlank(message = "type is mandatory")
    private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Validation> validations;


    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Media> medias;

    @ManyToOne()
    @JoinColumn(name = "level_id")
    private Level level;


    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;
    //@OneToMany(mappedBy = "question",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Temporization temporization;

    public void addValidation(Validation validation) {
        validations.add(validation);
    }

    public @NonNull Float getScorePoints() {
        if (validations == null || validations.isEmpty()) {
            return 0F;
        } else {
            return scorePoints = validations.stream().map(Validation::getScore).reduce(0F, Float::sum);
        }
    }
    public Integer getAnswersCount() {
        if (validations == null || validations.isEmpty()) {
            return 0;
        } else {
            return answersCount = validations.size();
        }
    }
    public Integer getCorrectAnswersCount() {
        if (validations == null || validations.isEmpty()) {
            return 0;
        } else {
            return correctAnswersCount = (int)validations.stream().map(Validation::getIsCorrect).filter(Boolean::booleanValue).count();
        }
    }
}
