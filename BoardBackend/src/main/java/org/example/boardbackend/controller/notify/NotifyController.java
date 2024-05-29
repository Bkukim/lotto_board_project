package org.example.boardbackend.controller.notify;

import lombok.RequiredArgsConstructor;
import org.example.boardbackend.security.jwt.JwtUtils;
import org.example.boardbackend.service.notify.NotifyService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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
public class NotifyController {
    private final NotifyService notifyService;
    private final JwtUtils jwtUtils;



    @GetMapping(value = "/subscribe",  produces = MediaType.TEXT_EVENT_STREAM_VALUE) //  SSE 스트림을 생성한다는 것을 나타냄. 클라이언트 이 미디어 타입을 사용하여 서버로부터 지속적인 이벤트 스트림을 받는다.
    public SseEmitter subscribe(@RequestParam String token
//                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId
    ) {
        return notifyService.subscribe(jwtUtils.getUserNameFromJwtToken(token));
    }
}