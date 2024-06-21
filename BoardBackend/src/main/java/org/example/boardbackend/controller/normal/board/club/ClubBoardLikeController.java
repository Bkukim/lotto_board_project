package org.example.boardbackend.controller.normal.board.club;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.club.ClubBoardLike;
import org.example.boardbackend.service.board.club.ClubBoardLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * packageName : org.example.boardbackend.controller.user.board.club
 * fileName : ClubBoardLikeController
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/normal/board/club/likes")
public class ClubBoardLikeController {
    private final ClubBoardLikeService clubBoardLikeService;

    // TODO: 좋아요 생성 함수
    @PostMapping("/create")
    public ResponseEntity<ClubBoardLike> createClubBoardLike(@RequestBody ClubBoardLike clubBoardLike) {
        ClubBoardLike createdLike = clubBoardLikeService.createClubBoardLike(clubBoardLike);
        return ResponseEntity.ok(createdLike);
    }

    // TODO: 좋아요 상태 확인 함수
    @GetMapping("/{userId}/{clubBoardId}")
    public ResponseEntity<ClubBoardLike> getLikeByUserIdAndClubBoardId(@PathVariable String userId, @PathVariable long clubBoardId) {
        ClubBoardLike clubBoardLike = clubBoardLikeService.getLikeByUserIdAndClubBoardId(userId, clubBoardId);
        if (clubBoardLike != null) {
            return ResponseEntity.ok(clubBoardLike);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // TODO: 좋아요 삭제 함수
    @DeleteMapping("/delete/{likeId}")
    public ResponseEntity<?> deleteClubBoardLike(@PathVariable Long likeId) {
        try {
            clubBoardLikeService.deleteClubBoardLike(likeId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // TODO: 좋아요 ID 조회 함수
    @GetMapping("/find/{userId}/{clubBoardId}")
    public ResponseEntity<Long> findLikeIdByUserIdAndClubBoardId(@PathVariable String userId, @PathVariable Long clubBoardId) {
        Optional<Long> likeId = clubBoardLikeService.findLikeIdByUserIdAndClubBoardId(userId, clubBoardId);
        return likeId.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
