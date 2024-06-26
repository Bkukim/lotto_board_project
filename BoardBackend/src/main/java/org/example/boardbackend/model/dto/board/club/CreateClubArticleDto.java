package org.example.boardbackend.model.dto.board.club;

import lombok.*;
import org.example.boardbackend.model.entity.board.club.FieldPic;

import java.util.List;

/**
 * packageName : org.example.boardbackend.model.dto.board.club
 * fileName : CreateClubArticleDto
 * author : BALLBAT
 * date : 2024-05-23
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23         BALLBAT          최초 생성
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateClubArticleDto {
    private long clubBoardId; // 동아리 게시판 번호, 기본키, 시퀀스
    private String userId; // userId, 참조키
    private long likes; // 좋아요 개수
    private String content; // 내용
    private String location; // 장소명
    private String address; // 주소
    private long participationFee; // 참가비
    private String startTime; // 활동 시작 시간
    private String endTime; // 활동 종료 시간
    private String recruitmentDeadline; // 모집 마감 시간
    private long maxQuota; // 모집 최대 정원
    private long minQuota; // 모집 최소 정원
    private String peoplesMatch; // 경기 인원 수
    private String material; // 경기 준비물
    private String sex; // 성별
    private String matchForm; // 경기 형식
    private String title; // 제목
    private List<FieldPicDto> fieldPics; // List of FieldPicDto
}