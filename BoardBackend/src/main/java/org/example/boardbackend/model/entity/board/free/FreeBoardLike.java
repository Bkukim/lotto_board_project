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

//todo:응답에 문제 있떤 이유 아래때문에

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "USER_ID")
//    private User userId;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "FREE_BOARD_ID")
//    private FreeBoard freeBoardId;


    @JoinColumn(name = "USER_ID")
    private String userId;

    @JoinColumn(name = "FREE_BOARD_ID")
    private long freeBoardId;

//    @Builder
//    public FreeBoardLike(User user, FreeBoard freeBoard) {
//        this.userId = user;
//        this.freeBoardId = freeBoard;
//    }

//@AllArgsConstructor이거 있으면 아래처럼 직접설정해줄 필요가 없음ㄷ
//    public FreeBoardLike(long likeId, String userId, long freeBoardId) {
//        this.likeId = likeId;
//        this.userId = userId;
//        this.freeBoardId = freeBoardId;
//    }
}
