package com.example.javachat.chat.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String messageId;

    @Column
    private String sender;

    @Column
    private String msgDesc;

    @Column
    private String msgType;

    @OneToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
}
