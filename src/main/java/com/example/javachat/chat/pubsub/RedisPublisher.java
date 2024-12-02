/*
package com.example.javachat.chat.pubsub;


import com.example.javachat.chat.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, MessageDTO message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}*/
