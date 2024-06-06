package org.example.boardbackend.model.dto.board.club;

import lombok.*;

/**
 * packageName : org.example.boardbackend.model.dto.board.club
 * fileName : ClubBoardWithPicsDto
 * author : BALLBAT
 * date : 2024-06-04
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-04         BALLBAT          최초 생성
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClubBoardWithPicsDto {
    private long clubBoardId;
    private String userId;
    private long likes;
    private String content;
    private String location;
    private String address;
    private long participationFee;
    private String startTime;
    private String endTime;
    private String recruitmentDeadline;
    private long maxQuota;
    private long minQuota;
    private String peoplesMatch;
    private String material;
    private String sex;
    private String matchForm;
    private String title;
    private String imgUrl;
}