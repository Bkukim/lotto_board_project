package org.example.boardbackend.repository.board.dept;

import org.example.boardbackend.model.dto.board.dept.DeptRecommentDto;
import org.example.boardbackend.model.entity.board.dept.DeptRecomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.boardbackend.repository.board.free
 * fileName : FreeBoardCommentRepository

 @@ -17,5 +23,16 @@ import org.springframework.stereotype.Repository;
  * 2024-05-30         PC          최초 생성
 */
@Repository
public interface DeptRecommentRepository extends JpaRepository<DeptRecomment, Long> {
    @Query(value = "SELECT DR.DEPT_BOARD_COMMENT_ID AS deptBoardCommentId, DR.USER_ID AS userId DR.CONTENT AS content, DR.INSERT_TIME AS insertTime \n" +
            "FROM LOTTO_DEPT_BOARD DB, LOTTO_DEPT_BOARD_COMMENT DC, LOTTO_DEPT_BOARD_RECOMMENT DR\n" +
            "WHERE DB.DEPT_BOARD_ID = DC.DEPT_BOARD_ID\n" +
            "AND DC.DEPT_BOARD_COMMENT_ID = DR.DEPT_BOARD_COMMENT_ID\n" +
            "AND DB.DEPT_BOARD_ID = :deptBoardId \n" +
            "ORDER BY DR.INSERT_TIME DESC",
            nativeQuery = true)
    List<DeptRecommentDto> findDeptBoardRecommentsByDeptBoardCommentIdOrderByInsertTimeDesc(@Param("deptBoardId") long deptBoardId);



}