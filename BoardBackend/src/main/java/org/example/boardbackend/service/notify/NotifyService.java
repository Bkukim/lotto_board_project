package org.example.boardbackend.service.notify;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.redis.MessageDto;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.repository.notify.EmitterRepository;
import org.example.boardbackend.repository.notify.NotifyRepository;
import org.example.boardbackend.service.redis.RedisMessageService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class NotifyService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60; // 연결 지속 시간

    private final EmitterRepository emitterRepository;
    private final NotifyRepository notifyRepository;
    private final RedisMessageService redisMessageService;

    public NotifyService(EmitterRepository emitterRepository, NotifyRepository notifyRepository,@Lazy RedisMessageService redisMessageService) {
        this.emitterRepository = emitterRepository;
        this.notifyRepository = notifyRepository;
        this.redisMessageService = redisMessageService;
    }

    // 구독함수
    public SseEmitter subscribe(String userId) {
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT); // emitter 생성
        SseEmitter emitter1 = emitterRepository.save(userId, sseEmitter);
        redisMessageService.subscribe(userId);

        // 오류 발생시 삭제
        sseEmitter.onCompletion(() -> emitterRepository.deleteById(userId));
        sseEmitter.onCompletion(() -> {
            emitterRepository.deleteById(userId);
            redisMessageService.removeSubscribe(userId); // 구독한 채널 삭제
        });
        sseEmitter.onTimeout(() -> emitterRepository.deleteById(userId));

        try {
            sseEmitter.send(SseEmitter.event().name("connect").data("로그인 성공"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sseEmitter;
    }
    public void publishNotificationToRedis(MessageDto messageDto){
        Notify notify = createNotification(messageDto.getReceiver(), messageDto.getNotificationType(),messageDto.getContent(),messageDto.getUrl());
        saveNotification(notify);
        redisMessageService.publish(messageDto);
    }

    // 알림 보내기 함수
    public void send(MessageDto messageDto) {
//        Notify notification = notifyRepository.save(createNotification(messageDto.getReceiver(), messageDto.getNotificationType(), messageDto.getContent(), messageDto.getUrl())); // 알림 객체 생성 및 저장
        log.debug("send 함수 들옴");
        log.debug(messageDto.getReceiver());
        SseEmitter sseEmitter = emitterRepository.findByUserId(messageDto.getReceiver());

        if (sseEmitter == null) {
            log.debug("sseEmitter is null, user is not connected.");
            return;
        }

        log.debug("보낼 알림 " + sseEmitter.toString());
        sendNotification(messageDto,sseEmitter);
    }

    private void sendNotification(MessageDto messageDto, SseEmitter emitter) {

        try {
            log.debug(emitter.toString());
            log.debug(messageDto.getNotificationType().toString());
            log.debug(messageDto.getContent());
            emitter.send(SseEmitter.event()
                    .name(messageDto.getNotificationType().toString())
                    .data(messageDto.getContent())
            );
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }


    public Notify createNotification(String userId, Notify.NotificationType notificationType, String content, String url) {
        return Notify.builder()
                .userId(userId)
                .notificationType(notificationType)
                .content(content)
                .url(url)
                .isRead("N")
                .build();
    }

    // todo 읽지 않은 알림 조회
    public List<Notify> findUnReadNotify(String userId) throws IOException {
        List<Notify> list = notifyRepository.findTop8ByUserIdOrderByInsertTimeDesc(userId/*,"N"*/);
        return list;
    }

    // todo 회원 전체 알림 읽음 변경함수
    public void updateIsRead(String userId) throws IOException {
        notifyRepository.updateByUserId(userId);
    }

    // todo 회원 특정 알림 읽음으로 변경함수
    public void updateIsReadByNotify(long notifyId) throws Exception{
        notifyRepository.updateByNotifyId(notifyId);
    }

    // todo 읽지 않은 알림 갯수 구하기
    public long countUnread(String userId) throws IOException {
        long count = notifyRepository.countByUserIdAndIsRead(userId, "N");
        return count;
    }
    // todo 알림 저장 함수
    public void saveNotification(Notify notify){
        notifyRepository.save(notify);
    }
}
