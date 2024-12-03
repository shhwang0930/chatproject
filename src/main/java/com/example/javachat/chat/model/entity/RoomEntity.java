package com.example.javachat.chat.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomIndex;
    @Column
    private String roomId;
    @Column
    private String roomName;
    @Column
    private int curCnt;
    @Column
    private String roomMng;
}
