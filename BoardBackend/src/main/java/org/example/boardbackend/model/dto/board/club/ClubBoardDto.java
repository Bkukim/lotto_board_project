package org.example.boardbackend.model.dto.board.club;

/**
 * packageName : org.example.boardbackend.model.dto.board.club
 * fileName : ClubBoardDto
 * author : BALLBAT
 * date : 2024-06-12
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-12         BALLBAT          최초 생성
 */
public interface ClubBoardDto {
    Long getClubBoardId();
    String getUserId();
    String getTitle();
    String getInsertTime();
    Long getLikes();
}
