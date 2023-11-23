package bouzhar.quiz.demo.media;


import bouzhar.quiz.demo.question.dto.QuestionResDto;
import bouzhar.quiz.demo.question.enums.MediaType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MediaDto {
    private Long id;
    private String url;
    @Enumerated(EnumType.STRING)
    private MediaType type;

}
