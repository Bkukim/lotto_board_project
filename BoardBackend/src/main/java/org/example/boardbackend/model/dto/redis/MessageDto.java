package org.example.boardbackend.model.dto.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.boardbackend.model.entity.notify.Notify;

import java.io.Serializable;

/**
 * packageName : org.example.boardbackend.model.dto.redis
 * fileName : MessageDto
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Notify.NotificationType notificationType; // 알림 타입
    private String content; // 전송할 메세지 내용
    private String receiver; // 메세지 수신자
    private String url; // 이동할 url
    private String roomId; // 메세지 방 번호 || 타겟 Channel

    public MessageDto(Notify.NotificationType notificationType, String content, String receiver, String url) {
        this.notificationType = notificationType;
        this.content = content;
        this.receiver = receiver;
        this.url = url;
    }
}