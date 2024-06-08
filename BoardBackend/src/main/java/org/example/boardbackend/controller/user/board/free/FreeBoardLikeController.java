package org.example.boardbackend.controller.user.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.free.FreeBoardLikeDto;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.example.boardbackend.service.board.free.FreeBoardLikeService;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/board-like")
public class FreeBoardLikeController {
    private final FreeBoardLikeService freeBoardLikeService;

//    @PostMapping("/insert-like/{userId}")
//    todo: ResponseResult쓰려면 HttpResponseEntity 생성해줘야함 :: 6월 8일 만들어두기
//    public ResponseResult<?> insert(@RequestBody @Validated FreeBoardLikeDto freeBoardLikeDto) throws Exception {
//        freeBoardLikeService.insert(freeBoardLikeDto);
//        return success(null);
//    }
//
//    @DeleteMapping("/delete-like/{userId}")
//    public ResponseResult<?> delete(@RequestBody @Valid HeartRequestDTO heartRequestDTO) {
//        heartService.delete(heartRequestDTO);
//        return success(null);
//    }
}
