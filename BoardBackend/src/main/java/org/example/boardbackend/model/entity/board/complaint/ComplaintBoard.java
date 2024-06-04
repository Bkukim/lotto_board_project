package org.example.boardbackend.model.entity.board.complaint;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.board.complaint
 * fileName : ComplaintBoard
 * author : hayj6
 * date : 2024-05-30(030)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30(030)         hayj6          최초 생성
 */
@Entity
@Table(name = "LOTTO_COMPLAINT_BOARD")
@SequenceGenerator(
        name = "SQ_COMPLAINT_BOARD_GENERATOR"
        , sequenceName = "SQ_COMPLAINT_BOARD"
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
public class ComplaintBoard extends BaseTimeEntity {
//    complaint_board_id	number
//    user_id	varchar2(16 byte)
//    likes	number
//    content	clob
//    status	varchar2(255 byte)
//    insert_time	varchar2(20 byte)
//    update_time	varchar2(20 byte)
//    title	varchar2(255 byte)
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE
        , generator = "SQ_COMPLAINT_BOARD_GENERATOR"
)
    private long complaintBoardId;
    private String userId;
    private long likes;
    @Lob
    private String content;
    private String status;
    private String title;
}
