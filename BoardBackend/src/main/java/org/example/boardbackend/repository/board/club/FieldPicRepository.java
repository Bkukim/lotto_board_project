package org.example.boardbackend.repository.board.club;

import org.example.boardbackend.model.entity.board.club.FieldPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.repository.board.club
 * fileName : FieldPicRepository
 * author : BALLBAT
 * date : 2024-05-30
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30         BALLBAT          최초 생성
 */
@Repository
public interface FieldPicRepository extends JpaRepository<FieldPic, String> {
    Optional<FieldPic> findByUuid(String uuid);
    List<FieldPic> findByClubBoard_ClubBoardId(Long clubBoardId);
}
