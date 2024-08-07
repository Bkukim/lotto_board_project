package org.example.boardbackend.repository.board.complaint;

import org.example.boardbackend.model.entity.board.complaint.ComplaintBoardLike;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName : org.example.boardbackend.repository.board.free
 * fileName : FreeBoardLikeRepository
 * author : KimDJ
 * date : 2024-06-05
 * description :
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-05         KimDJ          최초 생성
 */

@Repository
public interface ComplaintBoardLikeRepository extends JpaRepository<ComplaintBoardLike, Long> {

    Optional<ComplaintBoardLike> findByUserIdAndComplaintBoardId(String userId, Long complaintBoardId);

    boolean existsByComplaintBoardIdAndUserId(long complaintBoardId, String userId);

    ComplaintBoardLike findByUserIdAndComplaintBoardId(String userId, long complaintBoardId); // 좋아요 상태 조회용 메소드 추가


}
