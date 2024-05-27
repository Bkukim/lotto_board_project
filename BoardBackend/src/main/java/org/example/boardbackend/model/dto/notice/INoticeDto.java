package org.example.boardbackend.model.dto.notice;

/**
 * packageName : org.example.boardbackend.model.dto.notice
 * fileName : INoticeDto
 * author : PC
 * date : 2024-05-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21         PC          최초 생성
 */
public interface INoticeDto {
    long getNoticeId();
    String getTitle();
    String getContent();
    String getNoticeImgUrl();
    String getNoticeImgUuid();
    String eventYn();

}
