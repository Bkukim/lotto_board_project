package org.example.boardbackend.service.board.club;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.club.ClubBoardLike;
import org.example.boardbackend.repository.board.club.ClubBoardLikeRepository;
import org.example.boardbackend.repository.board.club.ClubBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.boardbackend.service.board.club
 * fileName : ClubBoardLikeService
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
@Slf4j
@Service
@RequiredArgsConstructor
public class ClubBoardLikeService {
    private final ClubBoardRepository clubBoardRepository;
    private final ClubBoardLikeRepository clubBoardLikeRepository;

    // TODO: 좋아요 생성 함수
    @Transactional
    public ClubBoardLike createClubBoardLike(ClubBoardLike clubBoardLike) {
        // 이미 같은 clubBoardId와 userId 조합이 존재하는지 확인
        if (clubBoardLikeRepository.existsByClubBoardIdAndUserId(clubBoardLike.getClubBoardId(), clubBoardLike.getUserId())) {
            throw new RuntimeException("Already liked by this user");
        }

        // clubBoard의 likes 컬럼을 +1
        ClubBoard clubBoard = clubBoardRepository.findById(clubBoardLike.getClubBoardId())
                .orElseThrow(() -> new RuntimeException("ClubBoard not found"));

        clubBoard.setLikes(clubBoard.getLikes() + 1);
        clubBoardRepository.save(clubBoard);

        return clubBoardLikeRepository.save(clubBoardLike);
    }

    // TODO: 좋아요 상태 확인 함수
    public ClubBoardLike getLikeByUserIdAndClubBoardId(String userId, long clubBoardId) {
        return clubBoardLikeRepository.findByUserIdAndClubBoardId(userId, clubBoardId);
    }

    // TODO: 좋아요 삭제 함수
    @Transactional
    public void deleteClubBoardLike(Long likeId) {
        ClubBoardLike clubBoardLike = clubBoardLikeRepository.findById(likeId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        // clubBoard의 likes 컬럼을 -1
        ClubBoard clubBoard = clubBoardRepository.findById(clubBoardLike.getClubBoardId())
                .orElseThrow(() -> new RuntimeException("ClubBoard not found"));

        clubBoard.setLikes(clubBoard.getLikes() - 1);
        clubBoardRepository.save(clubBoard);

        clubBoardLikeRepository.delete(clubBoardLike);
    }

    // TODO: 좋아요 ID 조회 함수
    public Optional<Long> findLikeIdByUserIdAndClubBoardId(String userId, Long clubBoardId) {
        Optional<ClubBoardLike> optionalClubBoardLike = clubBoardLikeRepository.findByUserIdAndClubBoardId(userId, clubBoardId);
        return optionalClubBoardLike.map(ClubBoardLike::getLikeId);
    }
}
