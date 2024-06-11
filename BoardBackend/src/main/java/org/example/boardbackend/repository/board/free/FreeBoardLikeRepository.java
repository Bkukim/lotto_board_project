package org.example.boardbackend.repository.board.free;

import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
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
public interface FreeBoardLikeRepository extends JpaRepository<FreeBoardLike, Long> {

    Optional<FreeBoardLike> findByUserAndFreeBoard(User user, FreeBoard freeBoard);
}
