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
    @Query(value = "SELECT DR.CONTENT AS content, DR.INSERT_TIME AS insertTime \n" +
            "FROM LOTTO_DEPT_BOARD FB, LOTTO_DEPT_BOARD_COMMENT FC, LOTTO_DEPT_BOARD_RECOMMENT FR\n" +
            "WHERE DB.FREE_BOARD_ID = DC.FREE_BOARD_ID\n" +
            "AND DC.FREE_BOARD_COMMENT_ID = DR.FREE_BOARD_COMMENT_ID\n" +
            "AND DB.FREE_BOARD_ID = :freeBoardId \n" +
            "ORDER BY DR.INSERT_TIME DESC",
            nativeQuery = true)
    List<DeptRecommentDto> findFreeBoardRecommentsByFreeBoardCommentIdOrderByInsertTimeDesc(@Param("freeBoardId") long freeBoardId);



}