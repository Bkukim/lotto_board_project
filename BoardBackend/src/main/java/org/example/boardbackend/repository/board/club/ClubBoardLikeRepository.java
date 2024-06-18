package org.example.boardbackend.repository.board.club;

import org.example.boardbackend.model.entity.board.club.ClubBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName : org.example.boardbackend.repository.board.club
 * fileName : ClubBoardLikeRepository
 * author : BALLBAT
 * date : 2024-06-17
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-17         BALLBAT          최초 생성
 */
@Repository
public interface ClubBoardLikeRepository extends JpaRepository<ClubBoardLike, Long> {
    Optional<ClubBoardLike> findByUserIdAndClubBoardId(String userId, Long clubBoardId);
    boolean existsByClubBoardIdAndUserId(long clubBoardId, String userId);
    ClubBoardLike findByUserIdAndClubBoardId(String userId, long clubBoardId); // 좋아요 상태 조회용 메소드 추가
}
