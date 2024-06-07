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
    @Column(name="LIKE_ID")
    private long likeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private FreeBoard freeBoard;


//    @JoinColumn(name = "USER_ID")
//    private String userId;
//
//    @JoinColumn(name = "FREE_BOARD_ID")
//    private long freeBoardId;

    @Builder
    public FreeBoardLike(User user, FreeBoard freeBoard) {
        this.user = user;
        this.freeBoard = freeBoard;
    }

//@AllArgsConstructor이거 있으면 아래처럼 직접설정해줄 필요가 없음ㄷ
//    public FreeBoardLike(long likeId, String userId, long freeBoardId) {
//        this.likeId = likeId;
//        this.userId = userId;
//        this.freeBoardId = freeBoardId;
//    }
}
