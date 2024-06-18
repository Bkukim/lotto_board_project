package org.example.boardbackend.repository.board.dept;

import org.example.boardbackend.model.entity.board.dept.DeptBoardReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.boardbackend.repository.board.dept
 * fileName : DeptReportRepository
 * author : PC
 * date : 2024-06-09
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-09         PC          최초 생성
 */
@Repository
public interface DeptBoardReportRepository extends JpaRepository<DeptBoardReport, Long> {
    //    todo : userId로 검색 -> 반복적으로 문제를 일으키는 사용자의 게시물을 검토할 때 유용
    @Query(value = "SELECT * FROM LOTTO_DEPT_BOARD_REPORT\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
            "ORDER BY INSERT_TIME DESC"
            , countQuery = "SELECT count(*)FROM LOTTO_DEPT_BOARD_REPORT\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"
            , nativeQuery = true)
    Page<DeptBoardReport> findDeptBoardReportsByUserIdContaining(@Param("userId") String userId, Pageable pageable);

}
