package org.example.boardbackend.model.dto.board.free;

import lombok.*;

/**
 * packageName : org.example.boardbackend.model.dto.board.free
 * fileName : FreeBoardLikeDto
 * author : KimDJ
 * date : 2024-06-05
 * description :
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-05         KimDJ          최초 생성
 */

public interface FreeBoardLikeDto {

    long getLikeId();
    String getUserId();
    long getFreeBoardId();


}
