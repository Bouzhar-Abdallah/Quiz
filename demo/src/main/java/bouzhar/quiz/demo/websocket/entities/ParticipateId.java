package bouzhar.quiz.demo.websocket.entities;

import bouzhar.quiz.demo.student.Student;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ParticipateId implements Serializable {

    @ManyToOne

    private Student student;

    @ManyToOne

    private Room room;

}
