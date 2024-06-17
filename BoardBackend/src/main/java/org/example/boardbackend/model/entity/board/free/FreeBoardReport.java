package org.example.boardbackend.model.entity.board.free;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.report
 * fileName : FreeBoardReport
 * author : KimDJ
 * date : 2024-05-22
 * description :
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-22         KimDJ          최초 생성
 */
@Entity
@Table(name = "FREE_BOARD_REPORT")
@SequenceGenerator(
        name = "SQ_FREE_BOARD_REPORT_GENERATOR"
        , sequenceName = "SQ_FREE_BOARD_REPORT"
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
public class FreeBoardReport extends BaseTimeEntity {
    //    시퀀스 명 바꾸기
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_FREE_BOARD_REPORT_GENERATOR"
    )
    private long reportId;
    private String userId;
    private long freeBoardId;
    private String content;


}
