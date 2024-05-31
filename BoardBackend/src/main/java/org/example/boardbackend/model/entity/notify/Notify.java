package org.example.boardbackend.model.entity.notify;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.example.boardbackend.model.entity.auth.User;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.notify
 * fileName : Notify
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
@Entity
@Table(name = "LOTTO_NOTIFY")
@SequenceGenerator(
        name = "SQ_NOTIFY_GENERATOR"
        , sequenceName = "SQ_NOTIFY"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Notify extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_NOTIFY_GENERATOR"
    )
    private long notifyId;

    private String userId;


    private String url;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String  isRead;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    public enum NotificationType{
        REPORT ,CLUB_APPLICATION , CLUB_APPROVAL, COMMENT, COMPLAINT, COMPLAINT_STATUS
    }
}

