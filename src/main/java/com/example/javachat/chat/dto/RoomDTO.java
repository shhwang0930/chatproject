package com.example.javachat.chat.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class RoomDTO implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String name;

    public static RoomDTO create(String name) {
        RoomDTO chatRoom = new RoomDTO();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }
}
