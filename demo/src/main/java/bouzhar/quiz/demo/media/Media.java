package bouzhar.quiz.demo.media;

import bouzhar.quiz.demo.question.Question;
import bouzhar.quiz.demo.question.enums.MediaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data

public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "url is mandatory")
    private String url;
    @Enumerated(EnumType.STRING)
    private MediaType type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;
}
