package org.example.boardbackend.model.entity.notice;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.constant.Event;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.notice
 * fileName : Winner
 * author : PC
 * date : 2024-06-03
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-03         PC          최초 생성
 */
@Entity
@Table(name = "LOTTO_WINNER")
@SequenceGenerator(
        name = "SQ_WINNER_GENERATOR"
        , sequenceName = "SQ_WINNER"
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
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_WINNER_GENERATOR"
    )
    private long winner_id;
    private String user_id;
    private long notice_id;

    public Winner(String user_id, long notice_id, Event event_type) {
        this.user_id = user_id;
        this.notice_id = notice_id;
        this.event_type = event_type;
    }

    @Enumerated(EnumType.STRING)
    private Event event_type;
}
