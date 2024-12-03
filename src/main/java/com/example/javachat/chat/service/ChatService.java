
package com.example.javachat.chat.service;

import com.example.javachat.chat.model.dto.MessageDTO;
import com.example.javachat.chat.model.entity.MessageEntity;
import com.example.javachat.chat.repository.MessageRepository;
import com.example.javachat.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChannelTopic channelTopic;
    private final MessageRepository messageRepository;


    public void sendMessage(MessageDTO message, String token) {
        String sender = jwtTokenProvider.getUserNameFromJwt(token);
        // 로그인 회원 정보로 대화명 설정
        message.setSender(sender);
        // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
        if (MessageDTO.MessageType.ENTER.equals(message.getType())) {
            message.setSender("[알림]");
            message.setMessage(sender + "님이 입장하셨습니다.");
        }
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSender(sender);
        messageEntity.setRoomId(message.getRoomId());
        messageEntity.setMsgDesc(message.getMessage());
        messageEntity.setMsgType(String.valueOf(message.getType()));
        messageRepository.save(messageEntity);
        // Websocket에 발행된 메시지를 redis로 발행(publish)
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


