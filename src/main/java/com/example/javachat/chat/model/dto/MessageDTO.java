package com.example.javachat.chat.model.dto;

import lombok.Data;

@Data
public class MessageDTO {
    public enum MessageType {
        ENTER, TALK;
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
