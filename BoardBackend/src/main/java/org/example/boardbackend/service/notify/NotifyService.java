package org.example.boardbackend.service.notify;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.repository.notify.EmitterRepository;
import org.example.boardbackend.repository.notify.NotifyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotifyService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60; // 연결 지속 시간

    private final EmitterRepository emitterRepository;
    private final NotifyRepository notifyRepository;

    // 구독함수
    public SseEmitter subscribe(String userId) {
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT); // emitter 생성
        SseEmitter emitter1 = emitterRepository.save(userId, sseEmitter);
        // 오류 발생시 삭제
        sseEmitter.onCompletion(() -> emitterRepository.deleteById(userId));
        sseEmitter.onTimeout(() -> emitterRepository.deleteById(userId));
        log.debug(emitter1.toString());
        try {
            sseEmitter.send(SseEmitter.event().name("connect").data("로그인 성공"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (emitterRepository.findAllEventCacheStartWithByUserId(userId) != null) { // 전송되지 않은 신호가 있다면 보내주기
            Map<String, SseEmitter> emitters = emitterRepository.findAllEventCacheStartWithByUserId(userId);
            emitters.forEach(
                    (key, emitter) -> {
                        try {
                            sseEmitter.send(SseEmitter.event().name("UNSENT_MESSAGE").data("확인하지 않은 알람이 있습니다."));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }else{
            try {
                sseEmitter.send(SseEmitter.event().name("connect").data("로그인 성공"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        return sseEmitter;
    }

    // 알림 보내기 함수
    public void send(User receiver, Notify.NotificationType notificationType, String content, String url) {
        Notify notification = notifyRepository.save(createNotification(receiver, notificationType, content, url)); // 알림 객체 생성 및 저장
        String userId = receiver.getUserId();
        log.debug("여기까진 들옴");
        log.debug(receiver.getUserId());
        SseEmitter sseEmitter = emitterRepository.findByUserId(userId);
        log.debug(sseEmitter.toString());
        sendNotification(userId,sseEmitter,notificationType, content);

    }

    private void sendNotification(String userId, SseEmitter emitter, Notify.NotificationType notificationType, String content) {
        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data(content)
            );

        } catch (Exception e) {
            log.debug("NotifyService Error");
            emitterRepository.deleteAllEmitterStartWithId(userId);
            emitterRepository.saveEventCache(userId,emitter); // 전달 되지 못한 emitter 들 저장
        }
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
