package org.example.boardbackend.service.board.complaint;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.config.WebConfig;
import org.example.boardbackend.model.dto.redis.MessageDto;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoard;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoardComment;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.repository.board.complaint.ComplaintBoardCommentRepository;
import org.example.boardbackend.repository.board.complaint.ComplaintBoardRepository;
import org.example.boardbackend.service.notify.NotifyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.boardbackend.service.board.complaint
 * fileName : ComplaintCommentService
 * author : BALLBAT
 * date : 2024-06-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-19         BALLBAT          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ComplaintCommentService {
    private final ComplaintBoardRepository complaintBoardRepository;
    private final ComplaintBoardCommentRepository complaintBoardCommentRepository;
    private final WebConfig webConfig;
    private final NotifyService notifyService;

    // TODO: 댓글 저장
    public void saveComment(ComplaintBoardComment complaintBoardComment){
        // 1. 댓글 저장
        complaintBoardCommentRepository.save(complaintBoardComment);

        // 2. 해당 게시글 상태를 "답변 완료"로 변경
        ComplaintBoard complaintBoard = complaintBoardRepository.findById(complaintBoardComment.getComplaintBoardId())
                .orElseThrow(() -> new RuntimeException("ComplaintBoard not found"));
        complaintBoard.setStatus("답변 완료");
        complaintBoardRepository.save(complaintBoard);
    }

    // todo 댓글 알림 저장 기능
    public void sendCommentNotification(ComplaintBoardComment complaintBoardComment) {
        ComplaintBoard complaintBoard = complaintBoardRepository.findById(complaintBoardComment.getComplaintBoardId())
                .orElseThrow(() -> new RuntimeException("complaintBoard not found"));
        String boardWriter = complaintBoard.getUserId();
        String notifyContent = "회원님의 건의사항에 답변이 등록되었습니다.     " + "\"" + complaintBoardComment.getContent() + "\"";

        String notifyUrl = "complaint/complaint-boardDetail/"+ complaintBoard.getComplaintBoardId();
        MessageDto messageDto = new MessageDto(Notify.NotificationType.COMMENT,notifyContent,boardWriter,notifyUrl);
        notifyService.publishNotificationToRedis(messageDto);
    }

    // TODO: 댓글 조회 함수
    public Page<ComplaintBoardComment> getCommentByComplaintBoardId(long complaintBoardId, Pageable pageable) {
        return complaintBoardCommentRepository.findComplaintBoardCommentsByComplaintBoardId(complaintBoardId, pageable);
    }

    // TODO: 댓글 삭제 함수
    @Transactional
    public void deleteComment(Long commentId) {
        // 1. 댓글 객체 가져오기
        ComplaintBoardComment complaintBoardComment = complaintBoardCommentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // 2. 댓글 삭제
        complaintBoardCommentRepository.deleteById(commentId);

        // 3. 게시글 상태 업데이트 (선택사항)
        ComplaintBoard complaintBoard = complaintBoardRepository.findById(complaintBoardComment.getComplaintBoardId())
                .orElseThrow(() -> new RuntimeException("ComplaintBoard not found"));

        // 상태를 다시 "확인중"으로 변경하는 로직 (필요에 따라 수정)
        complaintBoard.setStatus("확인중");
        complaintBoardRepository.save(complaintBoard);
    }
}
