package org.example.boardbackend.controller.user.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.free.FreeBoardLikeDto;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardComment;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.example.boardbackend.model.entity.notice.Notice;
import org.example.boardbackend.service.board.free.FreeBoardLikeService;
import org.example.boardbackend.service.board.free.FreeBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/api/user/free/board-like")
public class FreeBoardLikeController {
    private final FreeBoardLikeService freeBoardLikeService;
    private final FreeBoardService freeBoardService;


    //   todo: 저장함수 likeid 생성
//    @PostMapping("/save-like")
//    public ResponseEntity<String> saveLike(@RequestParam String userId, @RequestParam Long freeBoardId) {
//        try {
//            freeBoardLikeService.saveFreeBoardLike(userId, freeBoardId);
//            return new ResponseEntity<>("Like saved successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    todo: 조회함수 0618 하던중
//@GetMapping("/like-get/{userId}")
//public ResponseEntity<Object> getCommentsByFreeBoardId(@PathVariable String userId,
//                                                       @RequestParam(defaultValue = "0") long freeBoardId) {
//    try {
////        Pageable pageable = PageRequest.of(userId, freeBoardId);
////
////        // 해당 freeBoardId에 대한 댓글을 가져오도록 서비스 메서드를 호출
////        Page<FreeBoardComment> comments = freeBoardService.getCommentByFreeBoardId(freeBoardId, pageable);
////
////        if (comments.isEmpty() == false) {
////            // 공통 페이징 객체 생성 : 자료구조 맵 사용
////            Map<String, Object> response = new HashMap<>();
////
////            response.put("freeBoardComments", comments.getContent());       // faq 배열
////            response.put("currentPage", comments.getNumber());       // 현재페이지번호
////            response.put("totalItems", comments.getTotalElements()); // 총건수(개수)
////            response.put("totalPages", comments.getTotalPages());    // 총페이지수
////
////            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//    } catch (Exception e) {
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}

//   todo: 저장함수
    @PostMapping("/save-like/")
    public ResponseEntity<Object> createLike(
            @RequestBody FreeBoardLike freeBoardLike
    ) {
        try {
            FreeBoardLike freeBoardLike1 = freeBoardLikeService.save(freeBoardLike);
            return new ResponseEntity<>(freeBoardLike1, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //todo: 수정함수 like+1해주기, like테이블 + freeboardID에 like도 업데이트 되어야함
    @PutMapping("/update-like/{freeBoardId}")
    public  ResponseEntity<Object> update(
            @PathVariable long freeBoardId
            , @RequestBody FreeBoard freeBoard
    ){
        try {
            freeBoardService.save(freeBoard);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //todo: 삭제함수 like-1해주기, like테이블 + freeboardID에 like도 삭제되어야함

    @DeleteMapping("/deletion/{likeId}")
    public ResponseEntity<Object> delete(
            @PathVariable long likeId
    ) {
        try {
            boolean success = freeBoardLikeService.removeById(likeId);

            if (success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/insert-like/{userId}")
////    todo: ResponseResult쓰려면 HttpResponseEntity로 리턴값을 생성해줘야함(예제)
////     -> ResponseEntity써도 상관없음, 예제대로 안하고 원래쓰던 형식으로 씀
//    public ResponseEntity<?> insert(@RequestBody @Validated FreeBoardLikeDto freeBoardLikeDto) throws Exception {
//        try {
//            log.debug("컨트롤러1");
//            freeBoardLikeService.insert(freeBoardLikeDto);
//            log.debug("컨트롤러1.5");
//
//            return new ResponseEntity<>(HttpStatus.OK);
//
//        } catch (Exception e) {
//            log.debug("컨트롤러2"+e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    //@Valid와 @Validated 차이
//    @DeleteMapping("/delete-like/{userId}")
//    public ResponseEntity<?> delete(@RequestBody @Validated FreeBoardLikeDto freeBoardLikeDto) {
//        try {
//            freeBoardLikeService.delete(freeBoardLikeDto);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
