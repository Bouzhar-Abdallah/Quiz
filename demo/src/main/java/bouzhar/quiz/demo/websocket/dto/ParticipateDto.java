package bouzhar.quiz.demo.websocket.dto;

import bouzhar.quiz.demo.student.Student;
import bouzhar.quiz.demo.student.dto.StudentSimpleDto;
import bouzhar.quiz.demo.websocket.entities.ChatMessage;
import bouzhar.quiz.demo.websocket.entities.ParticipateId;
import bouzhar.quiz.demo.websocket.entities.Room;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class ParticipateDto {


    private LocalDate date;


    //@JoinColumn(name = "studentId")
    private StudentSimpleDto student;

    //@JoinColumn(name = "salonId")
    private RoomDto room;

}
