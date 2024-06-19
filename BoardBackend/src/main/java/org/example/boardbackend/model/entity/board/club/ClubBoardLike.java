package org.example.boardbackend.model.entity.board.club;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.board.club
 * fileName : ClubBoardLike
 * author : BALLBAT
 * date : 2024-06-17
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-17         BALLBAT          최초 생성
 */
@Entity
@Table(name = "LOTTO_CLUB_BOARD_LIKE")
@SequenceGenerator(
        name = "SQ_LOTTO_CLUB_BOARD_LIKE_GENERATOR",
        sequenceName = "SQ_LOTTO_CLUB_BOARD_LIKE",
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
public class ClubBoardLike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_LOTTO_CLUB_BOARD_LIKE_GENERATOR")
    @Column(name = "LIKE_ID")
    private long likeId;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "CLUB_BOARD_ID", nullable = false)
    private long clubBoardId;


    // 매개변수 있는 생성자 (필요에 따라 추가)
    public ClubBoardLike(Long clubBoardId, String userId) {
        this.clubBoardId = clubBoardId;
        this.userId = userId;
    }

    // Getter 및 Setter 메서드
    public Long getLikeId() {
        return likeId;
    }

    public Long getClubBoardId() {
        return clubBoardId;
    }

    public void setClubBoardId(Long clubBoardId) {
        this.clubBoardId = clubBoardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
