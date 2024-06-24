package org.example.boardbackend.service.board.complaint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.config.WebConfig;
import org.example.boardbackend.model.dto.board.complaint.ComplaintBoardDto;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.dto.redis.MessageDto;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoard;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoardComment;
import org.example.boardbackend.model.entity.board.dept.DeptBoard;
import org.example.boardbackend.model.entity.board.dept.DeptBoardReport;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardComment;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.repository.board.complaint.ComplaintBoardCommentRepository;
import org.example.boardbackend.repository.board.complaint.ComplaintBoardRepository;
import org.example.boardbackend.service.notify.NotifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.service.board.complaint
 * fileName : ComplaintBoardService
 * author : hayj6
 * date : 2024-05-30(030)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30(030)         hayj6          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ComplaintBoardService {

    private final ComplaintBoardRepository complaintBoardRepository;
    private final ComplaintBoardCommentRepository complaintBoardCommentRepository;
    private final NotifyService notifyService;
    private final WebConfig webConfig;
    @Value("${adminId}")
    private String adminId;

    //    todo 전체 조회
    public Page<ComplaintBoardDto> findComplaintBoardByTitleContaining(
            String title,
            Pageable pageable
    ) {
        Page<ComplaintBoardDto> page
                = complaintBoardRepository.findComplaintBoardByTitleContaining(
                title,
                pageable
        );
        return page;
    }

    //   todo 상세조회
    public Optional<ComplaintBoard> findById(long complaintBoardId) {
//        DB 상세조회 실행
        Optional<ComplaintBoard> complaintBoardOptional
                = complaintBoardRepository.findById(complaintBoardId); //crud레포짓토리
        return complaintBoardOptional;
    }

    // TODO 댓글 저장 기능
    // 1. boardId로 게시글 주인의 객체 가져오기,  1. 댓글을 저장, 2 알림 보내기
    public void saveComment(ComplaintBoardComment complaintBoardComment){
        complaintBoardCommentRepository.save(complaintBoardComment);
    }

    // todo 댓글 알림 저장 기능
    public void sendCommentNotification(ComplaintBoardComment complaintBoardComment) {
        ComplaintBoard complaintBoard = complaintBoardRepository.findById(complaintBoardComment.getComplaintBoardId())
                .orElseThrow(() -> new RuntimeException("freeBoard not found"));
        String boardWriter = complaintBoard.getUserId();
        String notifyContent = "회원님의 게시물에 댓글이 달렸습니다.     " + "\"" + complaintBoardComment.getContent() + "\"";
        String notifyUrl = "complaint/complaint-board/"+ complaintBoard.getComplaintBoardId();
        MessageDto messageDto = new MessageDto(Notify.NotificationType.COMMENT,notifyContent,boardWriter,notifyUrl);
        notifyService.publishNotificationToRedis(messageDto);
    }
    //   todo:  저장 함수
    public ComplaintBoard save(ComplaintBoard complaintBoard) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        ComplaintBoard complaintBoard1 = complaintBoardRepository.save(complaintBoard);
        return complaintBoard1;
    }
    // todo 건의 알림 저장 기능
    public void sendComplaintNotification(ComplaintBoard complaintBoard) {

        // 2. 알림 보내기
        String notifyContent = "건의사항 게시물이 등록되었습니다.   " + "\"" + complaintBoard.getTitle() + "\"";
        String notifyUrl = "complaint/complaint-boardDetail/" + complaintBoard.getComplaintBoardId(); // todo 주소 바꾸기
        MessageDto messageDto = new MessageDto(Notify.NotificationType.COMPLAINT, notifyContent, adminId, notifyUrl);
        notifyService.publishNotificationToRedis(messageDto);
    }
    // todo: 삭제
    public boolean removeById(long complaintBoardId) {
//        JPA 삭제함수 : deleteById(기본키)
//        1) 먼저 기본키가 테이블에 있으면 삭제, true 리턴
//           없으면 false 리턴
//        사용법 : jpa레포지토리.existsById(기본키)
//         => 기본키가 테이블에 있으지 확인. 있으면 true, 없으면 false
        if(complaintBoardRepository.existsById(complaintBoardId)) {
            complaintBoardRepository.deleteById(complaintBoardId);
            return true;
        } else {
            return false;
        }
    }

    //    todo : userId가 작성한 글 전체조회
    public Page<ComplaintBoardDto> findComplaintBoardByUserIdContaining(String userId, Pageable pageable)
    {
        Page<ComplaintBoardDto> page
                = complaintBoardRepository.findComplaintBoardByUserIdContaining(userId, pageable);
        return page;
    }

    //    todo : main 페이지 최신순 조회
    public List<ComplaintBoardDto> getLatestComplaintBoards(
            ComplaintBoardDto complaintBoardDto
    ) {
        List<ComplaintBoardDto> list
                = complaintBoardRepository.getLatestComplaintBoards(complaintBoardDto);
        return list;
    }
}
