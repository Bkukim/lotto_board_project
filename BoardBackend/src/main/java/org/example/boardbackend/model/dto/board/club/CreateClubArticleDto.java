package org.example.boardbackend.model.dto.board.club;

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
public class CreateClubArticleDto {
    private long clubBoardId;
    private String userId;
    private long like;
    private String content;
    private String location;
    private String address;
    private long maxQuota;
    private long minQuota;
    private long participationFee;
    private String startTime;
    private String endTime;
    private String sex;
    private String peoplesMatch;
    private String matchForm;
    private String material;
    private String recruitmentDeadline;
    private String imgUrl;
    private byte[] imgFile;

    public CreateClubArticleDto(long clubBoardId, String userId, long like, String content, String location, String address, long maxQuota, long minQuota, long participationFee, String startTime, String endTime, String sex, String peoplesMatch, String matchForm, String material, String recruitmentDeadline, String imgUrl, byte[] imgFile) {
        this.clubBoardId = clubBoardId;
        this.userId = userId;
        this.like = like;
        this.content = content;
        this.location = location;
        this.address = address;
        this.maxQuota = maxQuota;
        this.minQuota = minQuota;
        this.participationFee = participationFee;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sex = sex;
        this.peoplesMatch = peoplesMatch;
        this.matchForm = matchForm;
        this.material = material;
        this.recruitmentDeadline = recruitmentDeadline;
        this.imgUrl = imgUrl;
        this.imgFile = imgFile;
    }
}
