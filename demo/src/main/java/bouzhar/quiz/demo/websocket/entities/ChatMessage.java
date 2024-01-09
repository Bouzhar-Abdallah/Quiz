package bouzhar.quiz.demo.websocket.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ChatMessage {
    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", participate=" + participate +
                ", timeStamp=" + timeStamp +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    /*@JoinColumns({
            @JoinColumn(name = "student_id", referencedColumnName = "studentId"),
            @JoinColumn(name = "salon_id", referencedColumnName = "salonId")
    })*/
    private Participate participate;


    @Column()
    private LocalDateTime timeStamp;

}
