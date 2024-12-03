package com.example.javachat.chat.repository;

import com.example.javachat.chat.model.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaChatRoomRepository extends JpaRepository<RoomEntity, Integer> {

}
