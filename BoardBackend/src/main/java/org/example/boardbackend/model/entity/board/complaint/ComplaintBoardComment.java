package org.example.boardbackend.model.entity.board.complaint;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.board.free
 * fileName : FreeBoardComment
 * author : PC
 * date : 2024-05-30
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30         PC          최초 생성
 */
@Entity
@Table(name = "LOTTO_COMPLAINT_BOARD_COMMENT")
@SequenceGenerator(
        name = "SQ_COMPLAINT_BOARD_COMMENT_GENERATOR"
        , sequenceName = "SQ_COMPLAINT_BOARD_COMMENT"
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
public class ComplaintBoardComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_COMPLAINT_BOARD_COMMENT_GENERATOR"
    )
    private long complaintBoardCommentId;
    private String userId;
    private long complaintBoardId;
    private String content;

    public ComplaintBoardComment(String userId, long complaintBoardId, String content) {
        this.complaintBoardId=complaintBoardId;
        this.userId = userId;
        this.content = content;
    }
}
