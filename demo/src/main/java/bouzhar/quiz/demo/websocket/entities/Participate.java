package bouzhar.quiz.demo.websocket.entities;

import bouzhar.quiz.demo.student.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Participate {
    @EmbeddedId
    private ParticipateId participateId;


    private LocalDate date;

    @ManyToOne
    @MapsId("studentId")
    //@JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @MapsId("salonId")
    //@JoinColumn(name = "salonId")
    private Room room;

    @OneToMany(mappedBy = "participate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessages;

}
