package org.example.boardbackend.repository.board.dept;

import org.example.boardbackend.constant.DeptCode;
import org.example.boardbackend.model.dto.board.complaint.ComplaintBoardDto;
import org.example.boardbackend.model.dto.board.dept.DeptBoardDto;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.entity.board.dept.DeptBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.boardbackend.repository.board.dept
 * fileName : DeptBoardRepository
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
@Repository
public interface DeptBoardRepository extends JpaRepository<DeptBoard,Long> {
    @Query(value = "SELECT DEPT_BOARD_ID AS deptBoardId, USER_ID AS userId, TITLE AS title, INSERT_TIME AS insertTime, LIKES AS likes FROM LOTTO_DEPARTMENT_BOARD\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%' AND DEPT_ID = :deptId "
            , countQuery = "SELECT count(*)FROM LOTTO_DEPARTMENT_BOARD\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%' AND DEPT_ID = :deptId"
            , nativeQuery = true)
    Page<DeptBoardDto> findAllByTitleContaining(@Param("title") String title, @Param("deptId") String deptId, Pageable pageable);

    //    todo userId가 작성한 글 보기
    @Query(value = "SELECT DEPT_BOARD_ID AS deptBoardId, USER_ID AS userId, TITLE AS title, INSERT_TIME AS insertTime, LIKES AS likes FROM LOTTO_DEPARTMENT_BOARD\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
            "ORDER BY INSERT_TIME DESC"
            ,countQuery = "SELECT count (*) FROM LOTTO_DEPARTMENT_BOARD\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"
            ,nativeQuery = true)
    Page<DeptBoardDto> findDeptBoardByUserIdContaining(@Param("userId") String userId,
                                                                 Pageable pageable);
}
