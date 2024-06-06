package org.example.boardbackend.model.dto.board.free;

/**
 * packageName : org.example.boardbackend.model.dto.board.free
 * fileName : FreeBoardDto
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

public interface IFreeBoardRecommentDto {
     Long getFreeBoardRecommentId();
     Long getFreeBoardId();
     Long getFreeBoardCommentId();
     String getUserId();
     String getContent();
     String getInsertTime();
     String getUpdateTime();
}
