package org.example.boardbackend.model.entity.notice;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity
 * fileName : Event
 * author : PC
 * date : 2024-05-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21         PC          최초 생성
 */
@Entity
@Table(name = "NOTICE")
@SequenceGenerator(
        name = "SQ_NOTICE_GENERATOR"
        , sequenceName = "SQ_NOTICE"
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
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_NOTICE_GENERATOR"
    )
    private long noticeId;
    private String title;
    private String content;
    private byte[] noticeImg;
    private String noticeImgUrl;
    private String noticeImgUuid;
    private boolean eventYn;
    private long eventWinnerQuota;
}
