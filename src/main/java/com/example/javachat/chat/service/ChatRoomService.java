package com.example.javachat.chat.service;

import com.example.javachat.chat.model.dto.RoomDTO;
import com.example.javachat.chat.model.entity.RoomEntity;
import com.example.javachat.chat.repository.ChatRoomRepository;
import com.example.javachat.chat.repository.JpaChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final JpaChatRoomRepository jpaChatRoomRepository;
    public List<RoomDTO> getRooms() {
        return chatRoomRepository.findAllRoom();
    }

    public RoomDTO createRoom(String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String roomMng = auth.getName();
        RoomDTO chatRoom = RoomDTO.create(name);
        String roomId = chatRoom.getRoomId();

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomMng(roomMng);
        roomEntity.setRoomName(name);
        roomEntity.setRoomId(roomId);
        jpaChatRoomRepository.save(roomEntity);
        return chatRoomRepository.createChatRoom(chatRoom, roomId);
    }

    public RoomDTO roomInfo(String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
