package org.example.boardbackend.controller.notify;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.security.jwt.JwtUtils;
import org.example.boardbackend.service.notify.NotifyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * packageName : org.example.boardbackend.controller.notify
 * fileName : NotifyController
 * author : PC
 * date : 2024-05-29
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-29         PC          최초 생성
 */
@RestController
@RequestMapping("/api/v1/notify")
@RequiredArgsConstructor
@Slf4j
public class NotifyController {
    private final NotifyService notifyService;
    private final JwtUtils jwtUtils;



    @GetMapping(value = "/subscribe",  produces = "text/event-stream") //  SSE 스트림을 생성한다는 것을 나타냄. 클라이언트 이 미디어 타입을 사용하여 서버로부터 지속적인 이벤트 스트림을 받는다.
    public ResponseEntity<SseEmitter> subscribe(@RequestParam String token
    ) {
        try {
             SseEmitter sseEmitter = notifyService.subscribe(jwtUtils.getUserNameFromJwtToken(token));
            return new ResponseEntity<>(sseEmitter, HttpStatus.OK);
        }catch (Exception e){
            log.debug("알림오류"+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/unread-messages/{userId}")
    public ResponseEntity<Object> findUnReadNotify(@PathVariable String userId){
        try {
            List<Notify> notifies = notifyService.findUnReadNotify(userId);
            return new ResponseEntity<>(notifies,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // todo 회원 알림 전체 읽음으로 표시 함수
    @PutMapping("/mark/all/read/{userId}")
    public ResponseEntity<Object> markAsRead(@PathVariable String userId){
        try {
             notifyService.updateIsRead(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // todo 특정 알림 읽음으로 표시 함수
    @PutMapping("/mark/read/{notifyId}")
    public ResponseEntity<Object> markAsRead(@PathVariable long notifyId,
                                             @RequestBody Notify notify){
        try {
             notifyService.updateIsReadByNotify(notifyId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.debug("특정 알림 읽음으로 표시 함수"+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count-unread/{userId}")
    public ResponseEntity<Object> countUnread(@PathVariable String userId
                                             ){
        try {
            long count = notifyService.countUnread(userId);
            log.debug(String.valueOf(count));
            return new ResponseEntity<>(count,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}