package com.example.javachat.chat.controller;

import com.example.javachat.chat.model.dto.MessageDTO;
import com.example.javachat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {
    private final ChatService chatService;
    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(MessageDTO message
            //, @Header("token") String token
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String sender = auth.getName();
        chatService.sendMessage(message, sender);
    }
}