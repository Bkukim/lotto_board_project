package org.example.boardbackend.controller.user.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.free.FreeBoardLikeDto;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.example.boardbackend.service.board.free.FreeBoardLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/user/board-like")
public class FreeBoardLikeController {
    private final FreeBoardLikeService freeBoardLikeService;

    @PostMapping("/insert-like/{userId}")
//    todo: ResponseResult쓰려면 HttpResponseEntity로 리턴값을 생성해줘야함(예제)
//     -> ResponseEntity써도 상관없음, 예제대로 안하고 원래쓰던 형식으로 씀

    public ResponseEntity<?> insert(@RequestBody @Validated FreeBoardLikeDto freeBoardLikeDto) throws Exception {
        try {
            freeBoardLikeService.insert(freeBoardLikeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@Valid와 @Validated 차이
    @DeleteMapping("/delete-like/{userId}")
    public ResponseEntity<?> delete(@RequestBody @Validated FreeBoardLikeDto freeBoardLikeDto) {
        try {
            freeBoardLikeService.delete(freeBoardLikeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
