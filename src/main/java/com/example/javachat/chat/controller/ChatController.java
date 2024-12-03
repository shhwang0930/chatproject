package com.example.javachat.chat.controller;

import com.example.javachat.chat.model.dto.MessageDTO;
import com.example.javachat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;
    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(MessageDTO message
            //, @Header("token") String token
    ) {
        chatService.sendMessage(message);
    }
}