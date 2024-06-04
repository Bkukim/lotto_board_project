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
import java.util.List;
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

        try {
            sseEmitter.send(SseEmitter.event().name("connect").data("로그인 성공"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        if (emitterRepository.findAllEventCacheStartWithByUserId(userId) != null) { // 전송되지 않은 신호가 있다면 보내주기
//            List<Map<String,SseEmitter>> list = emitterRepository.findAllEventCacheStartWithByUserId(userId);
//            log.debug(list.toString());
//            for (int i = 0; i < list.size(); i++) {
//                try {
//                    SseEmitter sseEmitter1 = new SseEmitter(DEFAULT_TIMEOUT); // emitter 생성
//                    sseEmitter1.send(SseEmitter.event().name("UNSENT_MESSAGE").data("확인하지 않은 알림이 있습니다."));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            emitters.forEach(
//                    (key, emitter) -> {
//                        try {
//                            emitter.send(SseEmitter.event().name("UNSENT_MESSAGE").data("확인하지 않은 알람이 있습니다."));
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//            );
//            emitterRepository.deleteAllEventCacheStartWithId(userId);
//        }else{
//            try {
//                sseEmitter.send(SseEmitter.event().name("connect").data("로그인 성공"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return sseEmitter;
    }

    // 알림 보내기 함수
    public void send(String userId, Notify.NotificationType notificationType, String notifyContent, String url) {
        Notify notification = notifyRepository.save(createNotification(userId, notificationType, notifyContent, url)); // 알림 객체 생성 및 저장
        log.debug("send 함수 들옴");
        log.debug(userId);
        SseEmitter sseEmitter = emitterRepository.findByUserId(userId);
//        if (sseEmitter == null) {
//            log.debug("send 함수 : 로그인 안된 상태");
//            emitterRepository.deleteAllEmitterStartWithId(userId);
//            emitterRepository.saveEventCache(userId,new SseEmitter()); // 전달 되지 못한 emitter 들 저장
//            List<Map<String,SseEmitter>> list = emitterRepository.findAllEventCacheStartWithByUserId(userId);
//            log.debug("캐쉬 에 들어있는 알림들" + list.toString());
//        }else {
            log.debug("보낼 알림 "+sseEmitter.toString());
            sendNotification(userId, sseEmitter, notificationType, notifyContent);
//        }
    }

    private void sendNotification(String userId, SseEmitter emitter, Notify.NotificationType notificationType, String notifyContent) {

        try {
            log.debug(emitter.toString());
            log.debug(notificationType.toString());
            log.debug(notifyContent);
            emitter.send(SseEmitter.event()
                    .name(notificationType.toString())
                    .data(notifyContent)
            );
        } catch (IOException e) {
            log.debug(e.getMessage());
            log.debug("회원이 로그아웃 상태입니다.");
        }
    }


    private Notify createNotification(String userId, Notify.NotificationType notificationType, String content, String url) {
        return Notify.builder()
                .userId(userId)
                .notificationType(notificationType)
                .content(content)
                .url(url)
                .isRead("N")
                .build();
    }

    // todo 읽지 않은 알림 조회
    public List<Notify> findUnReadNotify(String userId) throws IOException{
        List<Notify> list = notifyRepository.findTop8ByUserIdOrderByIsReadAscInsertTimeDesc(userId/*,"N"*/);
        return list;
    }
    // todo 읽음 변경함수
    public void updateIsRead(String  userId) throws IOException{
        notifyRepository.updateByUserId(userId);
    }

    // todo 읽지 않은 알림 갯수 구하기
    public long countUnread(String userId)throws IOException{
        long count = notifyRepository.countByUserIdAndIsRead(userId,"N");
        return count;
    }
}
