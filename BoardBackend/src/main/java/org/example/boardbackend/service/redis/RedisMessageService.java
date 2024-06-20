package org.example.boardbackend.service.redis;

import lombok.RequiredArgsConstructor;
import org.example.boardbackend.model.dto.redis.MessageDto;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.boardbackend.service.redis
 * fileName : RedisMessageService
 * author : PC
 * date : 2024-06-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-19         PC          최초 생성
 */
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@RequiredArgsConstructor
@Service
public class RedisMessageService {

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriber subscriber; // 따로 구현한 Subscriber
    private final RedisTemplate<String, Object> redisTemplate;

    // 채널 구독
    public void subscribe( String receiver) {
        redisMessageListenerContainer.addMessageListener(subscriber, ChannelTopic.of(getChannelName(receiver)));
    }

    // 이벤트 발행
    public void publish( MessageDto messageDto) {
        messageDto.setRoomId(getChannelName(messageDto.getReceiver()));
        redisTemplate.convertAndSend(getChannelName(messageDto.getReceiver()), messageDto);
    }

    // 구독 삭제
    public void removeSubscribe(String receiver) {
        redisMessageListenerContainer.removeMessageListener(subscriber, ChannelTopic.of(getChannelName(receiver)));
    }

    private String getChannelName(String receiver) {
        return "REDIS_CHANNEL" + receiver;
    }
}