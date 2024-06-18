package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.dto.board.free.FreeBoardLikeDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.repository.board.free
 * fileName : FreeBoardLikeRepository
 * author : KimDJ
 * date : 2024-06-05
 * description :
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-05         KimDJ          최초 생성
 */

@Repository
public interface FreeBoardLikeRepository extends JpaRepository<FreeBoardLike, Long> {
//    Optional<FreeBoardLike> findByUserIdAndFreeBoardId(User user, FreeBoard freeBoard);
//    Optional<FreeBoardLike> findByUserAndFreeBoard(User user, FreeBoard freeBoard);
//    todo: 삭제하기 위해 해당 게시글에 유저의 id가 있는지 확인해야함, 조회하기
@Query(value = "SELECT *\n" +
        "FROM LOTTO_FREE_BOARD_LIKE\n" +
        "WHERE USER_ID LIKE '%'|| :userId ||'%'\n" +
        "AND FREE_BOARD_ID LIKE freeBoardId;"
        , countQuery = "SELECT count(*)FROM LOTTO_FREE_BOARD\n" +
        "WHERE TITLE LIKE '%'|| :userId ||'%'"
        , nativeQuery = true)
List<FreeBoardLikeDto> findAllByUserIdContainingAndFreeBoardId(@Param("userId") long freeBoardId);
}
