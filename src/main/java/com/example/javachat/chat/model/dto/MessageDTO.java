package com.example.javachat.chat.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDTO {

    @Builder
    public MessageDTO(MessageDTO.MessageType type, String roomId, String sender, String message, long userCount) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.userCount = userCount;
    }

    public enum MessageType {
        ENTER, TALK, QUIT, FILE;
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private long userCount;
}