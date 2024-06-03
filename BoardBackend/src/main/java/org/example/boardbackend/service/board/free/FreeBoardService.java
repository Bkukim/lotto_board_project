package org.example.boardbackend.service.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.config.WebConfig;
import org.example.boardbackend.model.dto.board.free.FreeBoardCommentDto;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardComment;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.repository.board.free.FreeBoardCommentRepository;
import org.example.boardbackend.repository.board.free.FreeBoardRepository;
import org.example.boardbackend.repository.user.UserRepository;
import org.example.boardbackend.service.notify.NotifyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private final FreeBoardRepository freeBoardRepository;
    private final UserRepository userRepository;
    private final NotifyService notifyService;
    private final FreeBoardCommentRepository freeBoardCommentRepository;
    private final WebConfig webConfig;

//    todo 전체 조회
    public Page<FreeBoardDto> selectByTitleContaining(
            String title,
            Pageable pageable
    ) {
        Page<FreeBoardDto> page
                = freeBoardRepository.findAllByTitleContaining(
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


//    // TODO 댓글 저장 기능
//    // 1. boardId로 게시글 주인의 객체 가져오기,  1. 댓글을 저장, 2 알림 보내기
//    public void saveComment(FreeBoardCommentDto freeBoardCommentDto){
//        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardCommentDto.getFreeBoardId()).get();
//
//        String boardWriter = freeBoard.getUserId();
//
//
//        FreeBoardComment freeBoardComment = new FreeBoardComment(freeBoardCommentDto.getUserId(),freeBoard,freeBoardCommentDto.getContent(),freeBoardCommentDto.getSecretCommentYn());
//
//        // 1. 댓글 저장
//        freeBoardCommentRepository.save(freeBoardComment);
//
//        // 2. 알림 보내기
//        String notifyContent = "게시물에 댓글이 달렸습니다.";
//        String notifyUrl = webConfig.getFrontDomain() + "/free/free-board/" + freeBoard.getFreeBoardId();
//        notifyService.send(boardWriter,Notify.NotificationType.COMMENT,notifyContent,notifyUrl);
//
//
//    }
    //   todo:  저장 함수
    public FreeBoard save(FreeBoard freeBoard) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        FreeBoard freeBoard1 = freeBoardRepository.save(freeBoard);
        return freeBoard1;
    }

    // todo: 삭제
    public boolean removeById(long freeBoardId) {
//        JPA 삭제함수 : deleteById(기본키)
//        1) 먼저 기본키가 테이블에 있으면 삭제, true 리턴
//           없으면 false 리턴
//        사용법 : jpa레포지토리.existsById(기본키)
//         => 기본키가 테이블에 있으지 확인. 있으면 true, 없으면 false
        if(freeBoardRepository.existsById(freeBoardId) == true) {
            freeBoardRepository.deleteById(freeBoardId);
            return true;
        } else {
            return false;
        }
    }

}
