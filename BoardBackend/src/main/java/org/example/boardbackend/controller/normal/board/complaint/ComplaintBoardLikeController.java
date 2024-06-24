package org.example.boardbackend.controller.normal.board.complaint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoardLike;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.example.boardbackend.service.board.complaint.ComplaintBoardLikeService;
import org.example.boardbackend.service.board.complaint.ComplaintBoardService;
import org.example.boardbackend.service.board.free.FreeBoardLikeService;
import org.example.boardbackend.service.board.free.FreeBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//import static jdk.vm.ci.hotspot.HotSpotCompilationRequestResult.success;

/**
 * packageName : org.example.boardbackend.controller.user.board.free
 * fileName : FreeBoardLikeController
 * author : KimDJ
 * date : 2024-06-07
 * description :
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-07         KimDJ          최초 생성
 */
@Slf4j
//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/normal/complaint/board-like")
public class ComplaintBoardLikeController {
    private final ComplaintBoardLikeService complaintBoardLikeService;
    private final ComplaintBoardService complaintBoardService;



    // TODO: 좋아요 생성 함수
    @PostMapping("/create")
    public ResponseEntity<ComplaintBoardLike> createComplaintBoardLike(@RequestBody ComplaintBoardLike complaintBoardLike) {
        ComplaintBoardLike complaintBoardLike1 = complaintBoardLikeService.createComplaintBoardLike(complaintBoardLike);
        return ResponseEntity.ok(complaintBoardLike);
    }

    // TODO: 좋아요 상태 확인 함수
    @GetMapping("/{userId}/{complaintBoardId}")
    public ResponseEntity<ComplaintBoardLike> getLikeByUserIdAndComplaintBoardId(@PathVariable String userId, @PathVariable long complaintBoardId) {
        ComplaintBoardLike complaintBoardLike1 = complaintBoardLikeService.getLikeByUserIdAndComplaintBoardId(userId, complaintBoardId);
        if (complaintBoardLike1 != null) {
            return ResponseEntity.ok(complaintBoardLike1);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // TODO: 좋아요 삭제 함수
    @DeleteMapping("/delete/{likeId}")
    public ResponseEntity<?> deleteComplaintBoardLike(@PathVariable Long likeId) {
        try {
            complaintBoardLikeService.deleteComplaintBoardLike(likeId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // TODO: 좋아요 ID 조회 함수
    @GetMapping("/get/{userId}/{complaintBoardId}")
    public ResponseEntity<Long> findLikeIdByUserIdAndFreeBoardId(@PathVariable String userId, @PathVariable Long complaintBoardId) {
        Optional<Long> likeId = complaintBoardLikeService.findLikeIdByUserIdAndComplaintBoardId(userId, complaintBoardId);
        return likeId.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
