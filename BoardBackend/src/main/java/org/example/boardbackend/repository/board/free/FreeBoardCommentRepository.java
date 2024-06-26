package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface FreeBoardCommentRepository extends JpaRepository<FreeBoardComment,Long> {
    Page<FreeBoardComment> findFreeBoardCommentsByFreeBoardIdOrderByInsertTimeDesc(long freeBoardId, Pageable pageable);
}
