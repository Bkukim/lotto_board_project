package org.example.boardbackend.service.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.redis.MessageDto;
import org.example.boardbackend.service.notify.NotifyService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * packageName : org.example.boardbackend.service.redis
 * fileName : RedisSubscriber
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
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> template;
    private final NotifyService notifyService;


    // 채널에 메세지가 오면 구독한 회원에게 일어날 현상들을 정의하는 곳이다.
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {

            // 전송 받은 메세지
            String publishMessage = template
                    .getStringSerializer().deserialize(message.getBody());

            MessageDto messageDto = objectMapper.readValue(publishMessage, MessageDto.class);

            log.info("Redis Subscribe Channel : " + messageDto.getRoomId());
            log.info("Redis SUB Message : {}", publishMessage);
            // 클라이언트에게 event 데이터 전송
            notifyService.send(messageDto);
        } catch (IOException e) {
            log.error("IOException is occurred. ", e);
        }
    }


}

