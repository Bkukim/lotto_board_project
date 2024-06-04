package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.boardbackend.repository.board.free
 * fileName : FreeBoardRepository
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
@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

//    @Query(value = "SELECT * FROM LOTTO_FREE_BOARD\n" +
//            "WHERE TITLE LIKE '%'|| :title ||'%'"
//            ,countQuery = "SELECT count (*) FROM LOTTO_FREE_BOARD\n" +
//            "WHERE TITLE LIKE '%'|| :title ||'%'"
//            ,nativeQuery = true)
//    Page<FreeBoard> findAllByTitleContaining(@Param("title") String title,
//                                       Pageable pageable
//    );


//    Page<FreeBoard> findAllByTitleContaining(@Param("title") String title, Pageable pageable);

    @Query(value = "SELECT FREE_BOARD_ID AS freeBoardId, USER_ID AS userId, TITLE AS title, INSERT_TIME AS insertTime, LIKES AS likes FROM LOTTO_FREE_BOARD\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , countQuery = "SELECT count(*)FROM LOTTO_FREE_BOARD\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , nativeQuery = true)
    Page<FreeBoardDto> findAllByTitleContaining(@Param("title") String title, Pageable pageable);

//    // content를 제외한 필드들만 조회하는 쿼리
//    @Query("SELECT new org.example.boardbackend.model.dto.board.free.FreeBoardSummary(freeBoard.freeBoardId, freeBoard.writer, freeBoard.likes, freeBoard.title) FROM FreeBoard freeBoard")
//    List<FreeBoardSummary> findAllWithoutContent();
}

