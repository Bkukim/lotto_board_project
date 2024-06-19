package org.example.boardbackend.controller.user.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.dto.board.free.IFreeBoardRecommentDto;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardComment;
import org.example.boardbackend.model.entity.board.free.FreeBoardRecomment;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.model.entity.board.free.FreeBoardReport;
import org.example.boardbackend.service.board.free.FreeBoardService;
import org.example.boardbackend.service.notify.NotifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.controller.user.free
 * fileName : FreeBoardController
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
@RestController
@RequestMapping("/api/user/board")
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;
    private final NotifyService notifyService;
    @Value("${adminId}")
    private String adminId;
    //    todo 전체 조회 + 제목 검색 + 페이징
    @GetMapping("/free")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<FreeBoardDto> freeBoard
                    = freeBoardService
                    .selectByTitleContaining(title, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("freeBoardList", freeBoard.getContent());       // faq 배열
            response.put("currentPage", freeBoard.getNumber());       // 현재페이지번호
            response.put("totalItems", freeBoard.getTotalElements()); // 총건수(개수)
            response.put("totalPages", freeBoard.getTotalPages());    // 총페이지수

            if (freeBoard.isEmpty() == false) {

//                조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    todo: 상세조회
    @GetMapping("/free/{freeBoardId}")
    public ResponseEntity<Object> findById(
            @PathVariable long freeBoardId
    ) {
        try {
//            상세조회 서비스 실행
            Optional<FreeBoard> freeBoardOptional
                    = freeBoardService.findById(freeBoardId);
            if (freeBoardOptional.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(freeBoardOptional.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



//    // TODO 댓글 저장 함수

//    @PostMapping("/free/comment")
//    public ResponseEntity<Object> saveFreeComment() {
//        try {
//            FreeBoardCommentDto freeBoardCommentDto
//                    = new FreeBoardCommentDto(1,
//                    "user12",
//                    2,
//                    "asdfasdf",
//                    "N"
//            );
//            freeBoardService.saveComment(freeBoardCommentDto);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            log.debug(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //    todo: 저장함수
    @PostMapping("/free/save")
    public ResponseEntity<Object> createFreeBoard(
            @RequestBody FreeBoard freeBoard
    ) {
        try {
//            DB 서비스 저장 함수 실행
            FreeBoard freeBoard1 = freeBoardService.save(freeBoard);
            log.debug("디버그" + freeBoard1.toString());
//            성공(OK) 메세지 + 저장된객체
            return new ResponseEntity<>(freeBoard1, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 수정 함수 : 수정 버튼 클릭시 실행될 함수
    @PutMapping("/free/update/{freeBoardId}")
    public ResponseEntity<Object> update(
            @PathVariable long freeBoardId,
            @RequestBody FreeBoard freeBoard
    ) {
        try {
            FreeBoard freeBoard1 = freeBoardService.save(freeBoard);  // 수정
            return new ResponseEntity<>(freeBoard1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 삭제 함수
    @DeleteMapping("/free/deletion/{freeBoardId}")
    public ResponseEntity<Object> delete(
            @PathVariable long freeBoardId
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = freeBoardService.removeById(freeBoardId);

            if (success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실행 : 0건 삭제(삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버(DB) 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 댓글 조회 함수
    @GetMapping("/free/{freeBoardId}/comments")
    public ResponseEntity<Object> getCommentsByFreeBoardId(@PathVariable long freeBoardId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            // 해당 freeBoardId에 대한 댓글을 가져오도록 서비스 메서드를 호출
            Page<FreeBoardComment> comments = freeBoardService.getCommentByFreeBoardId(freeBoardId, pageable);

            if (comments.isEmpty() == false) {
                // 공통 페이징 객체 생성 : 자료구조 맵 사용
                Map<String, Object> response = new HashMap<>();

                response.put("freeBoardComments", comments.getContent());       // faq 배열
                response.put("currentPage", comments.getNumber());       // 현재페이지번호
                response.put("totalItems", comments.getTotalElements()); // 총건수(개수)
                response.put("totalPages", comments.getTotalPages());    // 총페이지수

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 대댓글 조회 함수
    @GetMapping("/free/recomments/{freeBoardId}")
    public ResponseEntity<Object> getCommentsByFreeBoardCoId(@PathVariable long freeBoardId) {
        try {
            // 해당 freeBoardId에 대한 댓글을 가져오도록 서비스 메서드를 호출
            List<IFreeBoardRecommentDto> freeBoardRecomments = freeBoardService.getRecommentByFreeBoardId(freeBoardId);

            if (freeBoardRecomments.isEmpty() == false) {
                // 공통 페이징 객체 생성 : 자료구조 맵 사용


                return new ResponseEntity<>(freeBoardRecomments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug("대댓글 디버그 :: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO 댓글 저장 함수
    @PostMapping("/free/save/comment")
    public ResponseEntity<Object> saveFreeComment(@RequestBody FreeBoardComment freeBoardComment) {
        try {
            //            DB 서비스 저장 함수 실행
            freeBoardService.saveComment(freeBoardComment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("asdfasdf"+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO 대댓글 저장 함수
    @PostMapping("/free/save/recomment")
    public ResponseEntity<Object> saveFreeRecomment(@RequestBody FreeBoardRecomment freeBoardRecomment) {
        log.debug("대댓글"+freeBoardRecomment);

        try {
            //            DB 서비스 저장 함수 실행
            freeBoardService.saveRecomment(freeBoardRecomment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("디버그 :: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    todo: UserId 가 작성한 글 전체조회
    @GetMapping("/free/userId/freeBoard")
    public ResponseEntity<Object> findUserId(
            @RequestParam(defaultValue = "") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<FreeBoardDto> freeBoardDto
                    = freeBoardService.findFreeBoardByUserIdContaining(userId, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("freeBoardList", freeBoardDto.getContent());       // faq 배열
            response.put("currentPage", freeBoardDto.getNumber());       // 현재페이지번호
            response.put("totalItems", freeBoardDto.getTotalElements()); // 총건수(개수)
            response.put("totalPages", freeBoardDto.getTotalPages());    // 총페이지수

            if (freeBoardDto.isEmpty() == false) {
//                조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // todo 부서 신고 알림 함수
    @PostMapping("/free/report/save")
    public ResponseEntity<Object> reportDeptBoard(@RequestBody FreeBoardReport freeBoardReport) {
        try {
            // 신고 저장
            freeBoardService.saveReport(freeBoardReport);
            // 2. 알림 보내기
            FreeBoard freeBoard = freeBoardService.findById(freeBoardReport.getFreeBoardId()).get();
            String notifyContent = "게시물 신고가 접수되었습니다.   "  + "\"" + freeBoard.getTitle() + "\"";
            String notifyUrl = "free/free-boardDetail/" + freeBoard.getFreeBoardId(); // todo 주소 바꾸기
            notifyService.send(adminId, Notify.NotificationType.REPORT,notifyContent,notifyUrl);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("디버그 :: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
