package org.example.boardbackend.repository.board.complaint;

import org.example.boardbackend.model.entity.board.complaint.ComplaintBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.boardbackend.repository.board.complaint
 * fileName : ComplaintBoardRepository
 * author : hayj6
 * date : 2024-05-30(030)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30(030)         hayj6          최초 생성
 */
@Repository
public interface ComplaintBoardRepository extends JpaRepository<ComplaintBoard, Long> {

    Page<ComplaintBoard> findComplaintBoardByTitleContaining(@Param("title") String title,
                                                             Pageable pageable
    );
}
