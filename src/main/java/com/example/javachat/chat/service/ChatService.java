
package com.example.javachat.chat.service;

import com.example.javachat.chat.model.dto.MessageDTO;
import com.example.javachat.chat.model.entity.MessageEntity;
import com.example.javachat.chat.repository.ChatRoomRepository;
import com.example.javachat.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;
    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;


    /**
     * destination정보에서 roomId 추출
     */
    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    /**
     * 채팅방에 메시지 발송
     */
    public void sendChatMessage(MessageDTO message) {
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        log.info("--------------- userCnt : "+String.valueOf(chatRoomRepository.getUserCount(message.getRoomId())));
        if (MessageDTO.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 방에 입장했습니다.");
            message.setSender("[알림]");
        } else if (MessageDTO.MessageType.QUIT.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 방에서 나갔습니다.");
            message.setSender("[알림]");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String sender = message.getSender();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSender(sender);
        messageEntity.setRoomId(message.getRoomId());
        messageEntity.setMsgDesc(message.getMessage());
        messageEntity.setMsgType(String.valueOf(message.getType()));
        messageRepository.save(messageEntity);
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }

    public List<MessageDTO> getMessage(String roomId){
        List<MessageEntity> msgList = messageRepository.findByRoomIdOrderByMessageIdDesc(roomId);
        List<MessageDTO> dtoList = new ArrayList<>();

        for(MessageEntity msg : msgList){
            MessageDTO dto = new MessageDTO();
            dto.setMessage(msg.getMsgDesc());
            dto.setSender(msg.getSender());
            dto.setRoomId(msg.getRoomId());
            dto.setType(MessageDTO.MessageType.valueOf(msg.getMsgType()));
            dtoList.add(dto);
        }

        if(dtoList.isEmpty()){
            return null;
        }
        return dtoList;
    }

}




