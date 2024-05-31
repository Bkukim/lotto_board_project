package org.example.boardbackend.model.dto.notify;

import lombok.*;
import org.example.boardbackend.model.entity.notify.Notify;

public class NotifyDto {
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Response {
        String notifyId;
        String userId;
        String content;
        String notificationType;
        String insertTime;
        public static Response createResponse(Notify notify) {
            return Response.builder()
                    .content(notify.getContent())
                    .notifyId(String.valueOf(notify.getNotifyId()))
                    .userId(notify.getUserId())
                    .insertTime(notify.getInsertTime())
                    .build();

        }
    }
}
