package bouzhar.quiz.demo.websocket.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    /*@JoinColumns({
            @JoinColumn(name = "student_id", referencedColumnName = "studentId"),
            @JoinColumn(name = "salon_id", referencedColumnName = "salonId")
    })*/
    private Participate participate;


    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
