package org.example.boardbackend.service.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.dto.board.free.IFreeBoardRecommentDto;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.dto.redis.MessageDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.free.*;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.model.entity.board.free.FreeBoardReport;
import org.example.boardbackend.repository.board.free.FreeBoardCommentRepository;
import org.example.boardbackend.repository.board.free.FreeBoardRecommentRepository;
import org.example.boardbackend.repository.board.free.FreeBoardReportRepository;
import org.example.boardbackend.repository.board.free.FreeBoardRepository;
import org.example.boardbackend.repository.user.UserRepository;
import org.example.boardbackend.service.notify.NotifyService;
import org.example.boardbackend.service.redis.RedisMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.service.board.free
 * fileName : FreeBoardService
 * author : hayj6
 * date : 2024-05-27(027)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27(027)         hayj6          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FreeBoardService {

    private final FreeBoardReportRepository freeBoardReportRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final UserRepository userRepository;
    private final NotifyService notifyService;
    private final FreeBoardCommentRepository freeBoardCommentRepository;
    private final FreeBoardRecommentRepository freeBoardRecommentRepository;
    private final RedisMessageService redisMessageService;
    @Value("${adminId}")
    private String adminId;

    //    todo 전체 조회
    @Async
    public Page<FreeBoardDto> selectByTitleContaining(
            String title,
            Pageable pageable
    ) {
        Page<FreeBoardDto> page
                = freeBoardRepository.findByTitleContaining(
                title,
                pageable
        );
        return page;
    }

    //   todo 상세조회
    public Optional<FreeBoard> findById(long freeBoardId) {
//        DB 상세조회 실행
        Optional<FreeBoard> freeBoardOptional
                = freeBoardRepository.findById(freeBoardId); //crud레포짓토리
        return freeBoardOptional;
    }


    // TODO 댓글 저장 기능
    // 1. boardId로 게시글 주인의 객체 가져오기,  1. 댓글을 저장, 2 알림 보내기
    public void saveComment(FreeBoardComment freeBoardComment) {
        freeBoardCommentRepository.save(freeBoardComment);
    }

    // todo 댓글 알림 저장 기능
    public void sendCommentNotification(FreeBoardComment freeBoardComment) {
        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardComment.getFreeBoardId())
                .orElseThrow(() -> new RuntimeException("freeBoard not found"));
        String boardWriter = freeBoard.getUserId();
        String notifyContent = "회원님의 게시물에 댓글이 달렸습니다.     " + "\"" + freeBoardComment.getContent() + "\"";
        String notifyUrl = "free/free-boardDetail/" + freeBoard.getFreeBoardId();
        MessageDto messageDto = new MessageDto(Notify.NotificationType.COMMENT,notifyContent,boardWriter,notifyUrl);
        notifyService.publishNotificationToRedis(messageDto);
    }

    // TODO 대댓글 저장 기능
    // 1. boardId로 게시글 주인의 객체 가져오기,  1. 댓글을 저장, 2 알림 보내기
    public void saveRecomment(FreeBoardRecomment freeBoardRecomment) {
        freeBoardRecommentRepository.save(freeBoardRecomment);
    }

    // todo 대댓글 알림 저장 기능
    public void sendRecommentNotification(FreeBoardRecomment freeBoardRecomment) {
        FreeBoardComment freeBoardComment = freeBoardCommentRepository.findById(freeBoardRecomment.getFreeBoardCommentId())
                .orElseThrow(() -> new RuntimeException("FreeBoardComment not found"));
        String commentWriter = freeBoardComment.getUserId();
        String notifyContent = "회원님의 댓글에 또 다른 댓글이 달렸습니다.    " + "\"" + freeBoardRecomment.getContent() + "\"";
        String notifyUrl = "free/free-boardDetail/" + freeBoardComment.getFreeBoardId();
        MessageDto messageDto = new MessageDto(Notify.NotificationType.COMMENT,notifyContent,commentWriter,notifyUrl);
        notifyService.publishNotificationToRedis(messageDto);
    }


    //   todo:  저장 함수
    public FreeBoard save(FreeBoard freeBoard) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        FreeBoard freeBoard1 = freeBoardRepository.save(freeBoard);
        return freeBoard1;
    }

    // todo: 삭제 : 게시글 삭제 & 관리자 신고관리에서 삭제누를 시 실행 : soft delete
    public boolean removeById(long freeBoardId) {
//        JPA 삭제함수 : deleteById(기본키)
//        1) 먼저 기본키가 테이블에 있으면 삭제, true 리턴
//           없으면 false 리턴
//        사용법 : jpa레포지토리.existsById(기본키)
//         => 기본키가 테이블에 있으지 확인. 있으면 true, 없으면 false
        if (freeBoardRepository.existsById(freeBoardId) == true) {
            freeBoardRepository.deleteById(freeBoardId);
            return true;
        } else {
            return false;
        }
    }

    //    todo: 댓글 조회 함수
    public Page<FreeBoardComment> getCommentByFreeBoardId(long freeBoardId, Pageable pageable) {
        return freeBoardCommentRepository.findFreeBoardCommentsByFreeBoardIdOrderByInsertTimeDesc(freeBoardId, pageable);
    }



    //    todo: 대댓글 조회 함수
    public List<IFreeBoardRecommentDto> getRecommentByFreeBoardId(long freeBoardId) {
        return freeBoardRecommentRepository.findFreeBoardRecommentsByFreeBoardCommentIdOrderByInsertTimeDesc(freeBoardId);
    }

    // todo 대댓글 갯수 조회
    public Long countRecommentByFreeBoardCommentId(long commentId){
        return freeBoardRecommentRepository.countByFreeBoardCommentId(commentId);
    }

    //    todo : userId가 작성한 글 전체조회
    public Page<FreeBoardDto> findFreeBoardByUserIdContaining(String userId, Pageable pageable) {
        Page<FreeBoardDto> page
                = freeBoardRepository.findFreeBoardByUserIdContaining(userId, pageable);
        return page;
    }

    // todo : 신고 저장함수
    @Transactional
    public void saveReport(FreeBoardReport freeBoardReport) {
        freeBoardReportRepository.save(freeBoardReport);
    }

    //   todo : 관리자 : 신고 게시판 조회
    public Page<FreeBoardReport> findFreeBoardReportsByUserIdContaining(String userId, Pageable pageable) {

        Page<FreeBoardReport> reports = freeBoardReportRepository.findFreeBoardReportsByUserIdContaining(userId, pageable);

        return reports;
    }

    // todo 신고 알림 저장 기능
    public void sendReportNotification(FreeBoardReport freeBoardReport) {
        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardReport.getFreeBoardId())
                .orElseThrow(() -> new RuntimeException("FreeBoard not found"));
        // 2. 알림 보내기
        String notifyContent = "게시물 신고가 접수되었습니다.   "  + "\"" + freeBoard.getTitle() + "\"";
        String notifyUrl = "free/free-boardDetail/" + freeBoard.getFreeBoardId(); // todo 주소 바꾸기
        MessageDto messageDto = new MessageDto(Notify.NotificationType.REPORT,notifyContent,adminId,notifyUrl);
        notifyService.publishNotificationToRedis(messageDto);
    }

//    todo 신고게시글 삭제버튼 누를시 실행될 함수 : 자유게시판은 삭제
    public void deleteByFreeBoardId(long freeBoardId) {
        log.debug("tkdyd?");
//        freeBoardReportRepository.updateByFreeBoardId(freeBoardId);
        freeBoardRepository.deleteById(freeBoardId);
    }

    //    todo 신고게시글 삭제버튼 누를시 실행될 함수 : 신고테이블에서는 Y로 변경
    public void updateByReportId(long reportId) {
        freeBoardReportRepository.updateByFreeBoardId(reportId);
//        freeBoardRepository.deleteById(reportId);
    }

    //   todo : 관리자 : 신고 게시판 처리완료 조회
    public Page<FreeBoardReport> findFreeBoardReportsByUserIdAndIsProcessed(String userId, Pageable pageable) {

        Page<FreeBoardReport> reports = freeBoardReportRepository.findFreeBoardReportsByUserIdAndIsProcessed(userId, pageable);

        return reports;
    }



    //    todo : main 페이지 좋아요순 조회
    public List<FreeBoardDto> selectByTitleContainingMain(
            FreeBoardDto freeBoardDto
    ) {
        List<FreeBoardDto> list
                = freeBoardRepository.findFreeBoardByLikes(
                freeBoardDto
        );
        return list;
    }

    //    todo : main 페이지 최신순 조회
    public List<FreeBoardDto> getLatestFreeBoards(
            FreeBoardDto freeBoardDto
    ) {
        List<FreeBoardDto> list
                = freeBoardRepository.getLatestFreeBoards(freeBoardDto);
        return list;
    }

//    todo: hot 게시판 30개 까지 좋아요 조회
public List<FreeBoardDto> getHotBoard(
        FreeBoardDto freeBoardDto
) {
    List<FreeBoardDto> list
            = freeBoardRepository.findHotBoard(freeBoardDto);
    return list;
}
}
