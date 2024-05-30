package org.example.boardbackend.model.entity.board.free;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.example.boardbackend.model.entity.auth.User;
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
@Table(name = "LOTTO_FREE_BOARD_COMMENT")
@SequenceGenerator(
        name = "SQ_FREE_BOARD_COMMENT_GENERATOR"
        , sequenceName = "SQ_FREE_BOARD_COMMENT"
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
public class FreeBoardComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_FREE_BOARD_COMMENT_GENERATOR"
    )
    private long freeBoardCommentId;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User writer;
    @ManyToOne
    @JoinColumn(name = "FREE_BOARD_ID")
    private FreeBoard freeBoard;
    private String content;
    private String secretCommentYn;

    public FreeBoardComment(User writer, FreeBoard freeBoard, String content, String secretCommentYn) {
        this.writer = writer;
        this.freeBoard = freeBoard;
        this.content = content;
        this.secretCommentYn = secretCommentYn;
    }
}