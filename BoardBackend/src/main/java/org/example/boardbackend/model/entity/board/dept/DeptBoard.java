package org.example.boardbackend.model.entity.board.dept;

import jakarta.persistence.*;
import lombok.*;
import org.example.boardbackend.constant.DeptCode;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.example.boardbackend.model.common.BaseTimeEntity3;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.boardbackend.model.entity.board.dept
 * fileName : DeptBoard
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
@Table(name = "LOTTO_DEPARTMENT_BOARD")
@SequenceGenerator(
        name = "SQ_DEPARTMENT_BOARD_GENERATOR"
        , sequenceName = "SQ_DEPARTMENT_BOARD"
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
@SQLDelete(sql ="UPDATE LOTTO_DEPARTMENT_BOARD SET DELETE_YN = 'Y' WHERE DEPT_BOARD_ID = ?")
public class DeptBoard extends BaseTimeEntity3 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPARTMENT_BOARD_GENERATOR"
    )
    private long deptBoardId;
    private String userId;
    private String deptId;
    private long likes;
    private String title;
    @Lob
    private String content;

}
