package org.example.boardbackend.model.entity.board.free;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.example.boardbackend.model.common.BaseTimeEntity3;
import org.example.boardbackend.model.entity.auth.User;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : org.example.boardbackend.model.entity.board.free
 * fileName : FreeBoard
 * author : hayj6
 * date : 2024-05-27(027)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27(027)         hayj6          최초 생성
 */
@Entity
@Table(name = "LOTTO_FREE_BOARD")
@SequenceGenerator(
        name = "SQ_FREE_BOARD_GENERATOR"
        , sequenceName = "SQ_FREE_BOARD"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete jpa 어노테이션
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql ="UPDATE LOTTO_FREE_BOARD SET DELETE_YN = 'Y' WHERE FREE_BOARD_ID = ?")
public class FreeBoard extends BaseTimeEntity3{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_FREE_BOARD_GENERATOR"
    )
    private long freeBoardId;
    private String userId;

    @Column(name = "likes")
    private long likes;

    @Column(nullable = false)
    @Lob
    private String content;
    private String title;

}
