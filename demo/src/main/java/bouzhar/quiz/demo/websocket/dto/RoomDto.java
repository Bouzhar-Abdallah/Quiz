package bouzhar.quiz.demo.websocket.dto;

import bouzhar.quiz.demo.websocket.entities.Participate;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
public class RoomDto {

    private Long id;

    private String name;

}
