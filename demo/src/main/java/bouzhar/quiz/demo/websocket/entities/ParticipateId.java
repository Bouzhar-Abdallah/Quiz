package bouzhar.quiz.demo.websocket.entities;

import bouzhar.quiz.demo.student.Student;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ParticipateId implements Serializable {

    @ManyToOne

    private Student student;

    @ManyToOne

    private Room room;

}
