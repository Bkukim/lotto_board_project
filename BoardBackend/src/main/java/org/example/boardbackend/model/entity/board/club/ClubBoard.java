package org.example.boardbackend.model.entity.board.club;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.board
 * fileName : ClubBoard
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
@Entity
@Table(name = "LOTTO_CLUB_BOARD")
@SequenceGenerator(
        name = "SQ_LOTTO_CLUB_BOARD_GENERATOR",
        sequenceName = "SQ_LOTTO_CLUB_BOARD",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class ClubBoard extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_LOTTO_CLUB_BOARD_GENERATOR")
    private long clubBoardId; // 동아리 게시판 번호, 기본키, 시퀀스

    private String userId; // userId, 참조키
    private long likes; // 좋아요 개수
    @Lob
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
}