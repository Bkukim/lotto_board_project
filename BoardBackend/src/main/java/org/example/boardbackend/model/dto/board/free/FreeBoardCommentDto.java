package org.example.boardbackend.model.dto.board.free;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.board.free.FreeBoard;

/**
 * packageName : org.example.boardbackend.model.dto.board.free
 * fileName : FreeBoardCommentDto
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
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardCommentDto {
    private long FREE_BOARD_COMMENT_ID;
    private String userId;
    private long freeBoardId;
    private String content;
    private String secretCommentYn;
}
