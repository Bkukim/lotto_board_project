package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.entity.board.free.FreeBoardReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName : org.example.boardbackend.repository.board.free
 * fileName : FreeBoardReport
 * author : PC
 * date : 2024-06-17
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
            "AND IS_PROCESSED = 'N'"+
            "ORDER BY INSERT_TIME DESC"
            , countQuery = "SELECT count(*)FROM LOTTO_FREE_BOARD_REPORT\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"
            , nativeQuery = true)
    Page<FreeBoardReport> findFreeBoardReportsByUserIdContaining(@Param("userId") String userId, Pageable pageable);

    //    todo : 신고 게시물 삭제 버튼 누르면 실행되는 함수 : 상태 Y로 변경
//    @Query(value = "SELECT * FROM LOTTO_FREE_BOARD_REPORT\n" +
//            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
//            "AND IS_PROCESSED = 'N'"+
//            "ORDER BY INSERT_TIME DESC"
//            , countQuery = "SELECT count(*)FROM LOTTO_FREE_BOARD_REPORT\n" +
//            "WHERE USER_ID LIKE '%'|| :userId ||'%'"
//            , nativeQuery = true)
//    Page<FreeBoardReport> findFreeBoardReportsByUserIdContaining(@Param("userId") String userId, Pageable pageable);
    @Transactional
    @Modifying
    @Query("UPDATE FreeBoardReport e SET e.isProcessed = 'Y' WHERE e.reportId = :reportId")
    void updateByFreeBoardId(@Param("reportId") long reportId);

    //    todo : 신고 게시물 처리완료
    @Query(value = "SELECT * FROM LOTTO_FREE_BOARD_REPORT\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
            "AND IS_PROCESSED = 'Y'"+
            "ORDER BY INSERT_TIME DESC"
            , countQuery = "SELECT count(*)FROM LOTTO_FREE_BOARD_REPORT\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
            "AND IS_PROCESSED = 'Y'"+
            "ORDER BY INSERT_TIME DESC"
            , nativeQuery = true)
    Page<FreeBoardReport> findFreeBoardReportsByUserIdAndIsProcessed(@Param("userId") String userId, Pageable pageable);

}
