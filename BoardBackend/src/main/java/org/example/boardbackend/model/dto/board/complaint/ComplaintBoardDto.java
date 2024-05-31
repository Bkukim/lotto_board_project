package org.example.boardbackend.model.dto.board.complaint;

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

public interface ComplaintBoardDto {

     long getComplaintBoardId();
     String getUserId();
     long getLikes();
     String getContent();
     String getStatus();
     String getTitle();
     String getInsertTime();

}
