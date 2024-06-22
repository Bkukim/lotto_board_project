package org.example.boardbackend.controller.normal.board.dept;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.dept.DeptBoardLike;
import org.example.boardbackend.service.board.dept.DeptBoardLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//import static jdk.vm.ci.hotspot.HotSpotCompilationRequestResult.success;

/**
 * packageName : org.example.boardbackend.controller.user.board.dept
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
@RequestMapping("/api/normal/dept/board-like")
public class NormalDeptBoardLikeController {
    private final DeptBoardLikeService deptBoardLikeService;



    // TODO: 좋아요 생성 함수
    @PostMapping("/create")
    public ResponseEntity<DeptBoardLike> createFreeBoardLike(@RequestBody DeptBoardLike deptBoardLike) {
        DeptBoardLike deptBoardLike1 = deptBoardLikeService.createDeptBoardLike(deptBoardLike);
        return ResponseEntity.ok(deptBoardLike1);
    }

    // TODO: 좋아요 상태 확인 함수
    @GetMapping("/{userId}/{deptBoardId}")
    public ResponseEntity<DeptBoardLike> getLikeByUserIdAndFreeBoardId(@PathVariable String userId, @PathVariable long deptBoardId) {
        DeptBoardLike deptBoardLike = deptBoardLikeService.getLikeByUserIdAndDeptBoardId(userId, deptBoardId);
        if (deptBoardLike != null) {
            return ResponseEntity.ok(deptBoardLike);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // TODO: 좋아요 삭제 함수
    @DeleteMapping("/delete/{likeId}")
    public ResponseEntity<?> deleteFreeBoardLike(@PathVariable Long likeId) {
        try {
            deptBoardLikeService.deleteDeptBoardLike(likeId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // TODO: 좋아요 ID 조회 함수
    @GetMapping("/get/{userId}/{deptBoardId}")
    public ResponseEntity<Long> findLikeIdByUserIdAndFreeBoardId(@PathVariable String userId, @PathVariable Long deptBoardId) {
        Optional<Long> likeId = deptBoardLikeService.findLikeIdByUserIdAndDeptBoardId(userId, deptBoardId);
        return likeId.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
