package org.example.boardbackend.model.dto.board.free;

import lombok.AllArgsConstructor;
import org.example.boardbackend.model.entity.auth.User;

/**
 * packageName : org.example.boardbackend.model.dto.board.free
 * fileName : FreeBoardSummary
 * author : hayj6
 * date : 2024-05-31(031)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-31(031)         hayj6          최초 생성
 */
@AllArgsConstructor
public class FreeBoardSummary {
    private long freeBoardId;
    private User writer;
    private long likes;
    private String title;
}
