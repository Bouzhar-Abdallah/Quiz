package bouzhar.quiz.demo.websocket.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participate> participates;
}
