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
    String eventYn();
    String getEventYn();
    String getNoticeType();
    String getInsertTime();
    String getUpdateTime();
    long getViews();

//    todo: 이거 값넣어주는 거 맞나요?
    long getEventWinnerQuota = 0;



}
