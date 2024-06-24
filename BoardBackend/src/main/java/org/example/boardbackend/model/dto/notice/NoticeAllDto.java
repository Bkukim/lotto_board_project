package org.example.boardbackend.model.dto.notice;

/**
 * packageName : org.example.boardbackend.model.dto.notice
 * fileName : NoticeAllDto
 * author : KimDJ
 * date : 2024-05-24
 * description : 기본키와 제목만 가져오는  DTO
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-24         KimDJ          최초 생성
 */
public interface NoticeAllDto {
    Long getNoticeId();
    String getTitle();
    String getInsertTime();
    String getEventYN();
}
