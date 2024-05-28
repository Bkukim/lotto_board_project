package org.example.boardbackend.repository.board.club;

import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : org.example.boardbackend.repository.board
 * fileName : ClubBoardRepository
 * author : BALLBAT
 * date : 2024-05-23
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23         BALLBAT          최초 생성
 */
public interface ClubBoardRepository extends JpaRepository<ClubBoard, Long> {
}
