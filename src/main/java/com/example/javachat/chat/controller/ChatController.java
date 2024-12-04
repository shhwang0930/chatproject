package com.example.javachat.chat.controller;

import com.example.javachat.chat.model.dto.MessageDTO;
import com.example.javachat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;


@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {
    private final ChatService chatService;
    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(MessageDTO message, Principal principal) {
        if (principal != null) {
            String sender = principal.getName();
            message.setSender(sender);
            chatService.sendChatMessage(message);
        } else {
            log.warn("Principal is null.");
        }
    }
}