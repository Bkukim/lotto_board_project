package org.example.boardbackend.repositorytest.board.dept;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.config.WebConfig;
import org.example.boardbackend.model.dto.board.dept.DeptBoardDto;
import org.example.boardbackend.model.entity.board.dept.DeptBoard;
import org.example.boardbackend.model.entity.board.dept.DeptComment;
import org.example.boardbackend.model.entity.board.dept.DeptRecomment;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.repository.board.dept.DeptBoardRepository;
import org.example.boardbackend.repository.board.dept.DeptCommentRepository;
import org.example.boardbackend.repository.board.dept.DeptRecommentRepository;
import org.example.boardbackend.repository.user.UserRepository;
import org.example.boardbackend.repositorytest.notify.NotifyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
public class DeptBoardService {

    private final DeptBoardRepository deptBoardRepository;
    private final UserRepository userRepository;
    private final NotifyService notifyService;
    private final DeptCommentRepository deptCommentRepository;
    private final DeptRecommentRepository deptRecommentRepository;
    private final WebConfig webConfig;

    //    todo 전체 조회
    public Page<DeptBoardDto> selectByTitleContaining(
            String title,
            Pageable pageable
    ) {
        Page<DeptBoardDto> page
                = deptBoardRepository.findAllByTitleContaining(
                title,
                pageable
        );
        return page;
    }

    //   todo 상세조회
    public Optional<DeptBoard> findById(long freeBoardId) {
//        DB 상세조회 실행
        Optional<DeptBoard> freeBoardOptional
                = deptBoardRepository.findById(freeBoardId);
        return freeBoardOptional;
    }


    // TODO 댓글 저장 기능
    // 1. boardId로 게시글 주인의 객체 가져오기,  1. 댓글을 저장, 2 알림 보내기
    @Transactional
    public void saveComment(DeptComment deptComment) {
        DeptBoard deptBoard = deptBoardRepository.findById(deptComment.getDeptBoardId()).get();


        String boardWriter = deptBoard.getUserId();
        log.debug("여기는 서비스1");


//        FreeBoardComment freeBoardComment = new FreeBoardComment(freeBoardComment.getUserId(),freeBoard.getFreeBoardId(),freeBoardComment.getContent(),freeBoardComment.getSecretCommentYn());

        // 1. 댓글 저장
        deptCommentRepository.save(deptComment);
        log.debug("여기는 서비스2");

        // 2. 알림 보내기
        String notifyContent = "회원님의 게시물에 댓글이 달렸습니다." + /*\n" + "\"" +*/ deptComment.getContent() /*+ "\""*/;
        String notifyUrl = webConfig.getFrontDomain() + "/free/free-board/" + deptBoard.getDeptBoardId();
        notifyService.send(boardWriter, Notify.NotificationType.COMMENT, notifyContent, notifyUrl);
    }

    // TODO 대댓글 저장 기능
    // 1. boardId로 게시글 주인의 객체 가져오기,  1. 댓글을 저장, 2 알림 보내기
    @Transactional
    public void saveRecomment(DeptRecomment deptRecomment){
        DeptComment deptComment = deptCommentRepository.findById(deptRecomment.getDeptBoardCommentId()).get();

        String commentWriter = deptComment.getUserId();
        // 1. 댓글 저장
        deptRecommentRepository.save(deptRecomment);

        // 2. 알림 보내기
        String notifyContent = "회원님의 댓글에 또 다른 댓글이 달렸습니다.    "  + "\"" + deptRecomment.getContent() + "\"";
        String notifyUrl = webConfig.getFrontDomain() + "/free/free-board/" + deptComment.getDeptBoardId();
        notifyService.send(commentWriter,Notify.NotificationType.COMMENT,notifyContent,notifyUrl);
    }


    //   todo:  저장 함수
    @Transactional
    public void save(DeptBoard deptBoard) throws IOException {
       deptBoardRepository.save(deptBoard);
    }

    // todo: 삭제
    @Transactional
    public boolean removeById(long deptBoardId) throws IOException{
        if (deptBoardRepository.existsById(deptBoardId)) {
            deptBoardRepository.deleteById(deptBoardId);
            return true;
        } else {
            return false;
        }
    }

    //    todo: 댓글 조회 함수
    public Page<DeptComment> getCommentByDeptBoardId(long deptBoardId, Pageable pageable) throws IOException{
        Page<DeptComment> deptComments = deptCommentRepository.findDeptCommentByDeptBoardCommentIdOrderByInsertTimeDesc(deptBoardId, pageable);
        return deptComments;
    }

//    //    todo: 대댓글 조회 함수
//    public Page<FreeBoardRecomment> getRecommentByFreeBoardId(long freeBoardCommentId, Pageable pageable) {
//        return freeBoardRecommentRepository.findFreeBoardRecommentsByFreeBoardCommentIdOrderByInsertTimeDesc(freeBoardCommentId, pageable);
//    }
}
