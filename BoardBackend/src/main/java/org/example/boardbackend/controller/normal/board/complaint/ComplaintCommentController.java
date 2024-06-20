package org.example.boardbackend.controller.normal.board.complaint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoardComment;
import org.example.boardbackend.service.board.complaint.ComplaintBoardService;
import org.example.boardbackend.service.board.complaint.ComplaintCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * packageName : org.example.boardbackend.controller.user.board.complaint
 * fileName : ComplaintCommentController
 * author : BALLBAT
 * date : 2024-06-18
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-18         BALLBAT          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/board")
@RequiredArgsConstructor
public class ComplaintCommentController {
    private final ComplaintBoardService complaintBoardService;
    private final ComplaintCommentService complaintCommentService;

    // TODO 댓글 저장 함수
    @PostMapping("/complaint/save/comment")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> saveComplaintComment(@RequestBody ComplaintBoardComment complaintBoardComment) {
        try {
            // DB 서비스 저장 함수 실행
            complaintCommentService.saveComment(complaintBoardComment);
            complaintCommentService.sendCommentNotification(complaintBoardComment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO 댓글 삭제 함수
    @DeleteMapping("/complaint/delete/comment/{commentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteComplaintComment(@PathVariable Long commentId) {
        try {
            // DB 서비스 삭제 함수 실행
            complaintCommentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
