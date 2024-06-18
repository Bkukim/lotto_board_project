package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.entity.board.free.FreeBoardReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
 * 2024-06-17         PC          최초 생성
 */
@Repository
public interface FreeBoardReportRepository extends JpaRepository<FreeBoardReport,Long> {

}
