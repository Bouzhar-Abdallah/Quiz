package bouzhar.quiz.demo.subject.dto;

import lombok.Data;

@Data
public class SubjectReqDto {
    private Long id;
    private String name;
    private Long parent_id;
}
