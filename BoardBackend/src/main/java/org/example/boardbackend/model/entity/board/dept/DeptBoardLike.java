package org.example.boardbackend.model.entity.board.dept;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.board.dept
 * fileName : DeptBaordLike
 * author : PC
 * date : 2024-06-05
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-05         PC          최초 생성
 */
@Entity
@Table(name = "LOTTO_DEPT_BOARD_LIKE")
@SequenceGenerator(
        name = "SQ_DEPT_BOARD_LIKE_GENERATOR"
        , sequenceName = "SQ_DEPT_BOARD_LIKE"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
public class DeptBoardLike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT_BOARD_LIKE_GENERATOR"
    )
    private long likeId;
    private String userId;
    private long deptBoardId;

}