package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.entity.board.free.FreeBoardReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.boardbackend.repository.board.free
 * fileName : freeBoardReportRepository
 * author : hayj6
 * date : 2024-06-14(014)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-14(014)         hayj6          최초 생성
 */
@Repository
public interface FreeBoardReportRepository extends JpaRepository<FreeBoardReport, Long> {

    //    todo : userId로 검색 -> 반복적으로 문제를 일으키는 사용자의 게시물을 검토할 때 유용
    @Query(value = "SELECT * FROM LOTTO_FREE_BOARD_REPORT\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
            "ORDER BY INSERT_TIME DESC"
            , countQuery = "SELECT count(*)FROM LOTTO_FREE_BOARD_REPORT\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"
            , nativeQuery = true)
    Page<FreeBoardReport> findFreeBoardReportsByUserIdContaining(@Param("userId") String userId, Pageable pageable);

}
