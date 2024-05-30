package org.example.boardbackend.repository.notify;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {
    SseEmitter save(String userid, SseEmitter sseEmitter);
    void saveEventCache(String userid, SseEmitter sseEmitter);
    Map<String, SseEmitter> findAllEmitterStartWithByUserId(String userid);
    Map<String, SseEmitter> findAllEventCacheStartWithByUserId(String userid);
    void deleteById(String userid);
    void deleteAllEmitterStartWithId(String userid);
    void deleteAllEventCacheStartWithId(String userid);
    SseEmitter findByUserId(String userId);

}
