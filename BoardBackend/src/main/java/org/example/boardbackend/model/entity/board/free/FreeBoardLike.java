package org.example.boardbackend.model.entity.board.free;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.entity.auth.User;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import static jakarta.persistence.FetchType.LAZY;

/**
 * packageName : org.example.boardbackend.model.dto.board.free
 * fileName : FreeBoardLike
 * author : KimDJ
 * date : 2024-06-03
 * description :
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-03         KimDJ          최초 생성
 */
@Entity
@Table(name = "LOTTO_FREE_BOARD_LIKE")
@SequenceGenerator(
        name = "SQ_LOTTO_FREE_BOARD_LIKE_GENERATOR",
        sequenceName = "SQ_LOTTO_FREE_BOARD_LIKE",
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
public class FreeBoardLike {
    //    joinColumn =
//    엔티티 간의 관계를 매핑할 때 사용됩니다.
//    주로 관계형 데이터베이스에서 테이블 간의 관계를 정의하는 데 사용됩니다.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_LOTTO_FREE_BOARD_LIKE_GENERATOR")
    @Column(name = "LIKE_ID")
    private long likeId;

    @Column(name = "USER_ID", nullable = false)
    private String userId;
    // nullable = false 널값들어오지 못하게
    @Column(name = "FREE_BOARD_ID", nullable = false)
    private long freeBoardId;


    public long getLikeId() {return likeId;}

    // 사용자 아이디
    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    // 게시판 아이디
    public long getFreeBoardId() {return freeBoardId;}
    public void setFreeBoardId(long freeBoardId) {
        this.freeBoardId = freeBoardId;
    }
}
