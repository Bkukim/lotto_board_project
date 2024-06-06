package org.example.boardbackend.repository.board.dept;

import org.example.boardbackend.model.entity.board.dept.DeptComment;
import org.example.boardbackend.model.entity.board.free.FreeBoardComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface DeptCommentRepository extends JpaRepository<DeptComment,Long> {
    Page<DeptComment> findDeptCommentByDeptBoardCommentIdOrderByInsertTimeDesc(long freeBoardId, Pageable pageable);
}
