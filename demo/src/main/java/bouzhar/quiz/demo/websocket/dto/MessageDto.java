package bouzhar.quiz.demo.websocket.dto;

import bouzhar.quiz.demo.websocket.entities.Participate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private String content;
    private ParticipateDto participate;
    private LocalDateTime timeStamp;
}
