package com.example.javachat.chat.repository;

import com.example.javachat.chat.model.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository  extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findByRoomIdOrderByMessageIdDesc(String roomId);
}
