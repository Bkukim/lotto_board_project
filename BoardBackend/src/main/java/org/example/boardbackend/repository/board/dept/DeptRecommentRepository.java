package org.example.boardbackend.repository.board.dept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface DeptRecommentRepository extends JpaRepository<DeptRecommentRepository,Long> {
}
