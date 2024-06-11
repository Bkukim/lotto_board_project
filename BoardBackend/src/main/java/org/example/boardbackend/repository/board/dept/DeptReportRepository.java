package org.example.boardbackend.repository.board.dept;

import org.example.boardbackend.model.dto.board.dept.DeptBoardDto;
import org.example.boardbackend.model.entity.board.dept.DeptBoardReport;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface DeptReportRepository extends JpaRepository<DeptBoardReport,Long> {
}
