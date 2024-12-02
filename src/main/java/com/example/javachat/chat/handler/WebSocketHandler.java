/*
package com.example.javachat.chat.handler;


import com.example.javachat.chat.dto.MessageDTO;
import com.example.javachat.chat.dto.RoomDTO;
import com.example.javachat.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
// 삭제        TextMessage textMessage = new TextMessage("Welcome chatting sever~^^ ");
// 삭제       session.sendMessage(textMessage);
        MessageDTO chatMessage = objectMapper.readValue(payload, MessageDTO.class);
        RoomDTO room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }
}*/
