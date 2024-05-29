package org.example.boardbackend.model.entity.board.free;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.board.free
 * fileName : FreeBoard
 * author : hayj6
 * date : 2024-05-27(027)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27(027)         hayj6          최초 생성
 */
@Entity
@Table(name = "LOTTO_FREE_BOARD")
@SequenceGenerator(
        name = "SQ_FREE_BOARD_GENERATOR"
        , sequenceName = "SQ_FREE_BOARD"
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
public class FreeBoard extends BaseTimeEntity {
//    free_board_id	number
//    user_id	varchar2(16 byte)
//    likes	number
//    content	clob
//    insert_time	varchar2(20 byte)
//    update_time	varchar2(20 byte)
//    title	varchar2(255 byte)

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE
        , generator = "SQ_FREE_BOARD_GENERATOR"
)
    private long freeBoardId;
    private String userId;
    private long likes;
    private String content;
    private String title;
}
