package org.example.boardbackend.repository.board.club;

import org.example.boardbackend.model.entity.board.club.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.repository.board.club
 * fileName : ParticipantsRepository
 * author : BALLBAT
 * date : 2024-06-14
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-14         BALLBAT          최초 생성
 */
@Repository
public interface ParticipantsRepository extends JpaRepository<Participants, Long> {
    Optional<Participants> findByClubBoardIdAndUserId(long clubBoardId, String userId);

    List<Participants> findAllByClubBoardId(long clubBoardId);
}
