package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.dto.board.free.IFreeBoardRecommentDto;
import org.example.boardbackend.model.entity.board.free.FreeBoardRecomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.boardbackend.repository.board.free
 * fileName : FreeBoardCommentRepository
 * author : PC
 * date : 2024-05-30
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30         PC          최초 생성
 */
@Repository
public interface FreeBoardRecommentRepository extends JpaRepository<FreeBoardRecomment,Long> {
    @Query(value = "SELECT FR.FREE_BOARD_COMMENT_ID AS freeBoardCommentId, FR.USER_ID AS userId, FR.FREE_BOARD_COMMENT_ID AS freeBoardCommentId, FR.USER_ID AS userId, FR.CONTENT AS content, FR.INSERT_TIME AS insertTime \n" +

            "FROM LOTTO_FREE_BOARD FB, LOTTO_FREE_BOARD_COMMENT FC, LOTTO_FREE_BOARD_RECOMMENT FR\n" +
            "WHERE FB.FREE_BOARD_ID = FC.FREE_BOARD_ID\n" +
            "AND FC.FREE_BOARD_COMMENT_ID = FR.FREE_BOARD_COMMENT_ID\n" +
            "AND FB.FREE_BOARD_ID = :freeBoardId \n" +
            "ORDER BY FR.INSERT_TIME",
            nativeQuery = true)
    List<IFreeBoardRecommentDto> findFreeBoardRecommentsByFreeBoardCommentIdOrderByInsertTimeDesc(@Param("freeBoardId") long freeBoardId);

    List<FreeBoardRecomment> findAllByFreeBoardCommentId(long freeBoardCommentId);

    Long countByFreeBoardCommentId(long freeBoardCommentId);
}