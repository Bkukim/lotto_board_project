package org.example.boardbackend.model.dto.board.dept;

/**
 * packageName : org.example.boardbackend.model.dto.board.dept
 * fileName : DeptRecommentDto
 * author : PC
 * date : 2024-06-05
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-05         PC          최초 생성
 */
public interface DeptRecommentDto {
    Long getDeptBoardRecommentId();
    String getUserId();
    Long getDeptBoardCommentId();
    String getContent();
    String getLikes();
    String getSecretCommentYN();
    String getInsertTime();
}