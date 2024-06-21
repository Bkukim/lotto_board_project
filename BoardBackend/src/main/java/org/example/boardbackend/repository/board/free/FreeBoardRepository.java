package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.example.boardbackend.model.entity.board.free.FreeBoardReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

//    todo : title로 검색
    @Query(value = "SELECT FREE_BOARD_ID AS freeBoardId, USER_ID AS userId, TITLE AS title, INSERT_TIME AS insertTime, LIKES AS likes FROM LOTTO_FREE_BOARD\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"+
            "AND DELETE_YN = 'N'"+
            "ORDER BY INSERT_TIME DESC"
            , countQuery = "SELECT count(*)FROM LOTTO_FREE_BOARD\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"+
            "AND DELETE_YN = 'N'"
            , nativeQuery = true)
    Page<FreeBoardDto> findByTitleContaining(@Param("title") String title, Pageable pageable);



//    // content를 제외한 필드들만 조회하는 쿼리
//    @Query("SELECT new org.example.boardbackend.model.dto.board.free.FreeBoardSummary(freeBoard.freeBoardId, freeBoard.writer, freeBoard.likes, freeBoard.title) FROM FreeBoard freeBoard")
//    List<FreeBoardSummary> findAllWithoutContent();

    //    todo userId가 작성한 글 보기
    @Query(value = "SELECT FREE_BOARD_ID AS freeBoardId, USER_ID AS userId, TITLE AS title, INSERT_TIME AS insertTime, LIKES AS likes FROM LOTTO_FREE_BOARD\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
            "AND DELETE_YN = 'N'"+
            "ORDER BY INSERT_TIME DESC"
            ,countQuery = "SELECT count (*) FROM LOTTO_FREE_BOARD\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
            "AND DELETE_YN = 'N'"
            ,nativeQuery = true)
    Page<FreeBoardDto> findFreeBoardByUserIdContaining(@Param("userId") String userId,
                                               Pageable pageable);


//    todo: 메인에서 좋아요순으로 가져오기
@Query(value = "SELECT FREE_BOARD_ID, TITLE, LIKES\n" +
        "FROM (\n" +
        "    SELECT FREE_BOARD_ID, TITLE, LIKES\n" +
        "    FROM LOTTO_FREE_BOARD\n" +
        "    WHERE LIKES > 0\n" +
        "    ORDER BY LIKES DESC\n" +
        ")\n" +
        "WHERE ROWNUM <= 5"
        ,countQuery = "SELECT count(*)\n" +
        "FROM LOTTO_FREE_BOARD\n"
        ,nativeQuery = true)
List<FreeBoardDto> findFreeBoardByLikes(FreeBoardDto freeBoardDto);

    //    todo: 메인에서 최신순으로 가져오기
    @Query(value = "SELECT FREE_BOARD_ID, TITLE\n" +
            "FROM (\n" +
            "    SELECT FREE_BOARD_ID, TITLE\n" +
            "    FROM LOTTO_FREE_BOARD\n" +
            "    ORDER BY INSERT_TIME DESC\n" +
            ")\n" +
            "WHERE ROWNUM <= 5"
            ,countQuery = "SELECT count(*)\n" +
            "FROM LOTTO_FREE_BOARD\n"
            ,nativeQuery = true)
    List<FreeBoardDto> getLatestFreeBoards(FreeBoardDto freeBoardDto);

}

