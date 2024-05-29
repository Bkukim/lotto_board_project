package org.example.boardbackend.service.notify;


import lombok.RequiredArgsConstructor;
import org.example.boardbackend.model.dto.notify.NotifyDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.repository.notify.EmitterRepository;
import org.example.boardbackend.repository.notify.NotifyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotifyService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60; // 연결 지속 시간

    private final EmitterRepository emitterRepository;
    private final NotifyRepository notifyRepository;

    // 구독함수
    public SseEmitter subscribe(String userId) {
        String emitterId = makeTimeIncludeId(userId);
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));


        // 503 에러를 방지하기 위한 더미 이벤트 전송
        String eventId = makeTimeIncludeId(userId);
        sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + userId + "]");

//        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
//        if (hasLostData(lastEventId)) {
//            sendLostData(lastEventId, userId, emitterId, emitter);
//        }

        return emitter;
    }

    // emitter Id 만들어주는 함수
    private String makeTimeIncludeId(String email) {
        return email + "_" + System.currentTimeMillis();
    }

    private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("sse")
                    .data(data)
            );
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
        }
    }

    private boolean hasLostData(String lastEventId) {
        return !lastEventId.isEmpty();
    }

    private void sendLostData(String lastEventId, String userEmail, String emitterId, SseEmitter emitter) {
        Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByUserId(String.valueOf(userEmail));
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
    }

    // [2] send()
    //@Override
    public void send(User receiver, Notify.NotificationType notificationType, String content, String url) {
        Notify notification = notifyRepository.save(createNotification(receiver, notificationType, content, url)); // 알림 객체 생성 및 저장

        String receiverId = receiver.getUserId(); // 수신자의 이메일을 receiverEmail 변수에 저장
        String eventId = receiverId + "_" + System.currentTimeMillis(); // 이벤트 ID 생성
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByUserId(receiverId); // 수신자에 연결된 모든 SseEmitter 객체를 emitters 변수에 가져옴
        emitters.forEach( // emitters를 순환하며 각 SseEmitter 객체에 알림 전송
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, notification);
                    sendNotification(emitter, eventId, key, NotifyDto.Response.createResponse(notification));
                }
        );
    }

    private Notify createNotification(User receiver, Notify.NotificationType notificationType, String content, String url) {
        return Notify.builder()
                .receiver(receiver)
                .notificationType(notificationType)
                .content(content)
                .url(url)
                .isRead("N")
                .build();
    }


}
